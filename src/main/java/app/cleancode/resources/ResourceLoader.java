package app.cleancode.resources;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceLoader {
	/**
	 * Reads the given resource and returns the contents as a {@code String}.
	 * @param path the path to the resource
	 * @return the string contents of the specified resource
	 * @throws IOException if an i/o error occurs
	 * @throws IllegalArgumentException if the given path is null or blank.
	 */
public static String getResourceAsString(String path) throws IOException {
	if(path == null || path.isBlank()) {
		throw new IllegalArgumentException ("path may not be null or empty!");
	}
	InputStream in = ResourceLoader.class.getResourceAsStream(path);
	BufferedInputStream bufIn = new BufferedInputStream(in);
	InputStreamReader inReader = new InputStreamReader(bufIn);
	BufferedReader reader = new BufferedReader(inReader);
	String result = "";;
	try {
		String line = null;
		while((line =reader.readLine()) != null) {
			result = result.concat(line).concat("\n");
		}
	}catch (IOException e) {
		e.printStackTrace();
	}finally {
		in.close();
		bufIn.close();
		inReader.close();
		reader.close();
	}
	return result;
}
}
