package app.cleancode.graphics.shaders;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import app.cleancode.resources.ResourceLoader;

public class ShaderLoader {
private static Map<String, Integer> shaderMap = new HashMap<>();

/**
 * Gets the given shaders from the shader map, or trys to load them if they are not present.
 * 
 * @param name the name of the shaders
 * @return the id associated with the given shader
 */
public static int getShaders(String name) {
	Integer shaders = shaderMap.get(name);
	if(shaders == null || shaders == 0) {
		loadShaders(name);
		shaders = shaderMap.get(name);
		if(shaders == null || shaders == 0) {
			throw new RuntimeException ("failed to load shaders "+name);
		}
		shaderMap.put(name, shaders);
	}
	return shaders;
}

/**
 * Loades the given shaders into the shader map.
 * 
 * @param name the name of the shader
 */
private static void loadShaders(String name) {
	int shaders = 0;
	int vertShader = 0, fragShader = 0;
	try {
		vertShader = loadShaderResource("/shaders/"+name+".vert", ARBVertexShader.GL_VERTEX_SHADER_ARB);
		fragShader = loadShaderResource("/shaders/"+name+".frag", ARBFragmentShader.GL_FRAGMENT_SHADER_ARB);
	}catch (Exception e) {
		e.printStackTrace();
		return;
	}
	if(vertShader == 0 || fragShader == 0) {
		System.err.println("failed to load shaders for "+name);
		return;
	}
	shaders = ARBShaderObjects.glCreateProgramObjectARB();
	if(shaders == 0) {
		System.err.println("Failed to package shaders "+name);
		return;
	}
	ARBShaderObjects.glAttachObjectARB(shaders, vertShader);
	ARBShaderObjects.glAttachObjectARB(shaders, fragShader);
	ARBShaderObjects.glLinkProgramARB(shaders);
	if (ARBShaderObjects.glGetObjectParameteriARB(shaders, ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
		System.err.println("something went wrong while setting up the shader "+name);
		return;
	}
	ARBShaderObjects.glValidateProgramARB(shaders);
	if (ARBShaderObjects.glGetObjectParameteriARB(shaders, ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
		System.err.println("shader validation failed for shader "+name);
		return;
	}
	shaderMap.put(name, shaders);
}

private static int loadShaderResource(String path, int type) {
	int shader = 0;
	try {
		shader = ARBShaderObjects.glCreateShaderObjectARB(type);
		if(shader == 0) {
			System.err.println("could not create shader "+path);
			return 0;
		}
		ARBShaderObjects.glShaderSourceARB(shader, ResourceLoader.getResourceAsString(path));
		ARBShaderObjects.glCompileShaderARB(shader);
		if (ARBShaderObjects.glGetObjectParameteriARB(shader, ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
			throw new RuntimeException("could not compile sources for shader "+path);
		}
		return shader;
	}catch (Exception e) {
		ARBShaderObjects.glDeleteObjectARB(shader);
		e.printStackTrace();
		return 0;
	}
}

// the following methods are for manipulating uniforms to send to the shader.

private static Map<String, Integer> uniforms = new HashMap<>();

public static void createShaderUniform(String shaderName, String uniformName) {
	int location = GL30.glGetUniformLocation(getShaders(shaderName), uniformName);
	if(location < 0) {
		throw new RuntimeException ("Uniform "+uniformName+" does not exist in shaders "+shaderName);
	}
	uniforms.put(uniformName, location);
}

public static void setShaderUniform(String name, int value) {
	GL30.glUniform1i(uniforms.get(name), value);
}
public static void setShaderUniform(String name, float value) {
	GL30.glUniform1f(uniforms.get(name), value);
}
}
