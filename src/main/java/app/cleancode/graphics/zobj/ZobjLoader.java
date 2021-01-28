package app.cleancode.graphics.zobj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import app.cleancode.graphics.Obj3D;
import app.cleancode.graphics.texture.TextureLoader;

public class ZobjLoader {
public static Obj3D load(String name, int textureSamplerId) {
	try (InputStream resourceStream = ZobjLoader.class.getResourceAsStream(String.format("/models/%s.zobj", name))) {
		ZipInputStream unzipper = new ZipInputStream(resourceStream);
		BufferedInputStream bufUnzipper = new BufferedInputStream(unzipper);
		InputStreamReader bufUnzipperReader = new InputStreamReader(bufUnzipper);
		BufferedReader reader = new BufferedReader(bufUnzipperReader);
		ZipEntry entry;
		Obj3D result = null;
		int textureId = 0;
		boolean foundTexture = false;
		while((entry = unzipper.getNextEntry()) != null) {
			if(entry.getName().equals(name+".obj")) {
		result = new Obj3D(reader, textureSamplerId, textureId);
			}else if(entry.getName().equals(name+"-diffuse.png")) {
				if(result == null) {
					textureId = TextureLoader.loadTexture(unzipper);
				}else {
					result.textureId = TextureLoader.loadTexture(unzipper);
				}
				foundTexture = true;
			}
		}
		unzipper.close();
		bufUnzipper.close();
		bufUnzipperReader.close();
		reader.close();
		if(result == null || !foundTexture) {
			throw new RuntimeException ("zobj file "+name+" is incomplete");
		}
		result.prepareDraw();
		return result;
	}catch (Exception e) {
		throw new RuntimeException (e);
	}
}
}
