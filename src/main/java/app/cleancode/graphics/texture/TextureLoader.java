package app.cleancode.graphics.texture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import app.cleancode.graphics.decoder.ImageDecoder;

public class TextureLoader {
public static int loadTexture(InputStream stream) throws IOException {
	ByteBuffer bytes = ImageDecoder.decode(stream, ImageDecoder.FORMAT_RGBA);
	int textureId = GL11.glGenTextures();
	GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
	GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
	GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, bytes.getInt(), bytes.getInt(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytes);
	GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
	return textureId;
}
}
