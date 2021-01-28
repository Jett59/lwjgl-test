package app.cleancode.graphics.decoder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class ImageDecoder {
	public static int FORMAT_RGBA = 1;
	public static int FORMAT_RGB = 0;

	/**
	 * decodes the specified {@code InputStream} as an image into a {@code ByteBuffer}.
	 * The width and height (int) are both inserted at the start of the buffer, and all pixels come after that.
	 * 
	 * @param stream the input stream to decode
	 * @param outFormat the output format
	 * @return a {@code ByteBuffer} containing the decoded bytes
	 * @throws IllegalArgumentException if the outFormat is not valid
	 * @throws NullPointerException if the stream is null
	 * @throws IOException if an i/o error occurs
	 */
public static ByteBuffer decode(InputStream stream, int outFormat) throws IOException {
	if(stream == null) {
		throw new NullPointerException("cannot decode a null input stream!");
	}
	BufferedImage image = ImageIO.read(stream);
	int width = image.getWidth();
	int height = image.getHeight();
	ByteBuffer buffer;
	if(outFormat == FORMAT_RGB) {
		buffer = ByteBuffer.allocateDirect(3*width*height+Integer.BYTES*2);
	}else if(outFormat == FORMAT_RGBA) {
		buffer = ByteBuffer.allocateDirect(4*width*height+Integer.BYTES*2);
	}else {
		throw new IllegalArgumentException ("out format "+outFormat+" is not valid");
	}
	buffer.putInt(width).putInt(height);
	for(int y = 0; y < height; y++) {
		for(int x = 0; x < width; x++) {
			int argb = image.getRGB(x, y);
			byte b = (byte)(argb & 255);
			argb = argb >> 8;
		byte g = (byte)(argb & 255);
		argb = argb >> 8;
		byte r = (byte)(argb & 255);
		if(outFormat == FORMAT_RGB || outFormat == FORMAT_RGBA) {
			buffer.put(r)
			.put(g)
			.put(b);
			if(outFormat == FORMAT_RGBA) {
				argb = argb >> 8;
				byte a = (byte)(argb & 255);
				buffer.put(a);
			}
		}else {
			throw new IllegalArgumentException ("out format "+outFormat+" is not valid");
		}
		}
	}
	buffer.flip();
	return buffer;
}
}
