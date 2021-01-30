package app.cleancode.graphics;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.system.MemoryUtil;

import static org.lwjgl.opengl.GL30.*;

public class Obj3D extends Node {
private int vao = 0;
private int numVbos = 0;
private List<Integer> vbos;
private List<List<float[][]>> faces;
	public int textureId;
	private boolean ready = false;
	private int numVertices = 0;

	public Obj3D(BufferedReader reader, int textureSamplerId, int textureId) throws IOException {
		this.textureId = textureId;
		this.vbos = new ArrayList<>();
		this.faces = new ArrayList<>();
		load(reader);
	}

public void load(BufferedReader reader) throws IOException {
	String line = null;
	List<float[]> vertices = new ArrayList<>();
	List<float[]> textureVertices = new ArrayList<>();
	List<float[]> vertexNormals = new ArrayList<>();
	List<List<float[][]>> faces = new ArrayList<>();
	while((line = reader.readLine()) != null) {
		line = line.trim();
		if(line.startsWith("v ")) {
			String[] coords = line.split(" +");
			if(coords.length > 3) {
				float x = Float.parseFloat(coords[1]);
				float y = Float.parseFloat(coords[2]);
				float z = Float.parseFloat(coords[3]);
				float w;
				if(coords.length > 4) {
					w = Float.parseFloat(coords[4]);
				}else {
					w = 1f;
				}
				vertices.add(new float[] {x, y, z, w});
			}
		}else if(line.startsWith("vn")) {
			String[] coords = line.split(" +");
			if(coords.length > 3) {
				float x = Float.parseFloat(coords[1]);
				float y = Float.parseFloat(coords[2]);
				float z = Float.parseFloat(coords[3]);
				float w;
				if(coords.length > 4) {
					w = Float.parseFloat(coords[4]);
				}else {
					w = 1f;
				}
				vertexNormals.add(new float[] {x, y, z, w});
			}
		}else if(line.startsWith("vt")) {
			String[] coords = line.split(" +");
			if(coords.length > 2) {
				float u = Float.parseFloat(coords[1]);
				float v = Float.parseFloat(coords[2]);
				textureVertices.add(new float[] {u, v});
			}
		} else if(line.startsWith("f ")) {
			String[] vertexSets = line.split(" +");
			List<float[][]> face = new ArrayList<>(vertexSets.length-1);
			for(int i = 1; i < vertexSets.length; i++) {
				String[] vertexVertices = vertexSets[i].split("/");
				if(vertexVertices.length == 3) {
					int vertexNum = Integer.parseInt(vertexVertices[0]);
					int textureVertexNum = Integer.parseInt(vertexVertices[1]);
					int vertexNormalNum = Integer.parseInt(vertexVertices[2]);
					float[] vertex = vertices.get(vertexNum-1);
					float[] textureVertex = textureVertices.get(textureVertexNum-1);
					float[] vertexNormal = vertexNormals.get(vertexNormalNum-1);
					face.add(new float[][] {vertex, textureVertex, vertexNormal});
				}
			}
			faces.add(face);
		}
	}
	vertices.clear();
	textureVertices.clear();
	vertexNormals.clear();
	this.faces = faces;
}

	@Override
	public void glDraw() {
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, textureId);
		glBindVertexArray(vao);
		for(int i = 0; i < numVbos; i++) {
			glEnableVertexAttribArray(i);
		}
		glDrawArrays(GL_TRIANGLES, 0, numVertices);
		glBindVertexArray(0);
	}

	public void prepareDraw() {
		if(ready) {
			throw new IllegalStateException("Error: this Obj3D is already prepared for rendering");
		}
		int size = 0;
		for(List<float[][]> vertices : faces) {
			int numVertices = (vertices.size()-2)*3;
			size+=numVertices;
		}
		this.numVertices = size;
		FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(size*4);
		FloatBuffer textureVertexBuffer = MemoryUtil.memAllocFloat(size*4);
		for(List<float[][]> face : faces) {
			if(face.size() >= 3) {
				int numberTriangles = face.size()-2;
				float[][] first = face.get(0);
				float[][] last = null;
				int lastIndex = 2;
				for(int triangleNum = 0; triangleNum < numberTriangles; triangleNum++) {
					float[][] next;
					int nextIndex = lastIndex+1;
					if(last == null) {
						next = face.get(lastIndex-1);
						last = face.get(lastIndex);
					}else {
						next = face.get(nextIndex);
						lastIndex = nextIndex;
					}
					vertexBuffer.put(first[0]).put(last[0]).put(next[0]);
					textureVertexBuffer.put(first[1]).put(last[1]).put(next[1]);
					last = next;
				}
			}
		}
		vertexBuffer.flip();
		try {
			createVao();
			createVbo(vertexBuffer);
			createVbo(textureVertexBuffer);
			bindVbos();
		}catch (Exception e) {
			System.err.println("error occured creating vertex buffer:");
			e.printStackTrace();
		}finally {
			MemoryUtil.memFree(vertexBuffer);
			MemoryUtil.memFree(textureVertexBuffer);
		}
	}

	private void bindVbos() {
		if(numVbos != vbos.size()) {
			throw new IllegalStateException("numVbos not properly aligned with vbos.size!");
		}
		glBindVertexArray(vao);
		for(int i = 0; i < numVbos; i++) {
			int vbo = vbos.get(i);
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glVertexAttribPointer(i, 4, GL_FLOAT, false, 0, vbo);
		}
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	private void createVbo(FloatBuffer buffer) {
		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		vbos.add(vbo);
		numVbos++;
	}

	private void createVao() {
		if(vao != 0) {
			throw new IllegalStateException("vao already initialized!");
		}
		vao = glGenVertexArrays();
	}

	public boolean isReady() {
		return ready;
	}

}
