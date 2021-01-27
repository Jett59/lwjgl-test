package app.cleancode.graphics.zobj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipInputStream;

import app.cleancode.graphics.Obj3D;

public class ZobjLoader {
public static Obj3D load(String name) {
	try (InputStream resourceStream = ZobjLoader.class.getResourceAsStream(String.format("/models/%s.zobj", name))) {
		ZipInputStream unzipper = new ZipInputStream(resourceStream);
		unzipper.getNextEntry();
		BufferedInputStream bufUnzipper = new BufferedInputStream(unzipper);
		InputStreamReader bufUnzipperReader = new InputStreamReader(bufUnzipper);
		BufferedReader reader = new BufferedReader(bufUnzipperReader);
		Obj3D result = new Obj3D(reader);
		resourceStream.close();
		unzipper.close();
		bufUnzipper.close();
		bufUnzipperReader.close();
		reader.close();
		return result;
	}catch (Exception e) {
		throw new RuntimeException (e);
	}
}
}
