package app.cleancode.graphics;
import java.io.BufferedReader;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
/**
 * @author Jeremy Adams (elias4444)
 *
 * Use these lines if reading from a file
 * FileReader fr = new FileReader(ref);
 * BufferedReader br = new BufferedReader(fr);
 * Use these lines if reading from within a jar
 * InputStreamReader fr = new InputStreamReader(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(ref)));
 * BufferedReader br = new BufferedReader(fr);
 */
public class Obj3D implements Drawable {
	private ArrayList<float[]> vertexsets = new ArrayList<>();
	private ArrayList<float[]> vertexsetsnorms = new ArrayList<>();
	private ArrayList<float[]> vertexsetstexs = new ArrayList<>();
	private ArrayList<int[]> faces = new ArrayList<>();
	private ArrayList<int[]> facestexs = new ArrayList<>();
	private ArrayList<int[]> facesnorms = new ArrayList<>();
	private int objectlist;
	private int numpolys = 0;
	public float toppoint = 0;
	public float bottompoint = 0;
	public float leftpoint = 0;
	public float rightpoint = 0;
	public float farpoint = 0;
	public float nearpoint = 0;
	public Obj3D(BufferedReader ref) {
		loadobject(ref);
		prepareDraw();
		numpolys = faces.size();
	}

	private void loadobject(BufferedReader br) {
		try {
			String newline;
			boolean firstpass = true;
			while (((newline = br.readLine()) != null)) {
				newline = newline.trim();
				if (newline.length() > 0) {
					if (newline.charAt(0) == 'v' && newline.charAt(1) == ' ') {
						float[] coords = new float[4];
						String[] coordstext = new String[4];
						coordstext = newline.split("\\s+");
						for (int i = 1;i < coordstext.length;i++) {
							coords[i-1] = Float.valueOf(coordstext[i]).floatValue();
						}
						if (firstpass) {
							rightpoint = coords[0];
							leftpoint = coords[0];
							toppoint = coords[1];
							bottompoint = coords[1];
							nearpoint = coords[2];
							farpoint = coords[2];
							firstpass = false;
						}
						if (coords[0] > rightpoint) {
							rightpoint = coords[0];
						}
						if (coords[0] < leftpoint) {
							leftpoint = coords[0];
						}
						if (coords[1] > toppoint) {
							toppoint = coords[1];
						}
						if (coords[1] < bottompoint) {
							bottompoint = coords[1];
						}
						if (coords[2] > nearpoint) {
							nearpoint = coords[2];
						}
						if (coords[2] < farpoint) {
							farpoint = coords[2];
						}
						vertexsets.add(coords);
					}
					if (newline.charAt(0) == 'v' && newline.charAt(1) == 't') {
						float[] coords = new float[4];
						String[] coordstext = new String[4];
						coordstext = newline.split("\\s+");
						for (int i = 1;i < coordstext.length;i++) {
							coords[i-1] = Float.valueOf(coordstext[i]).floatValue();
						}
						vertexsetstexs.add(coords);
					}
					if (newline.charAt(0) == 'v' && newline.charAt(1) == 'n') {
						float[] coords = new float[4];
						String[] coordstext = new String[4];
						coordstext = newline.split("\\s+");
						for (int i = 1;i < coordstext.length;i++) {
							coords[i-1] = Float.valueOf(coordstext[i]).floatValue();
						}
						vertexsetsnorms.add(coords);
					}
					if (newline.charAt(0) == 'f' && newline.charAt(1) == ' ') {
						String[] coordstext = newline.split("\\s+");
						int[] v = new int[coordstext.length - 1];
						int[] vt = new int[coordstext.length - 1];
						int[] vn = new int[coordstext.length - 1];
						for (int i = 1;i < coordstext.length;i++) {
							String fixstring = coordstext[i].replaceAll("//","/0/");
							String[] tempstring = fixstring.split("/");
							v[i-1] = Integer.valueOf(tempstring[0]).intValue();
							if (tempstring.length > 1) {
								vt[i-1] = Integer.valueOf(tempstring[1]).intValue();
							} else {
								vt[i-1] = 0;
							}
							if (tempstring.length > 2) {
								vn[i-1] = Integer.valueOf(tempstring[2]).intValue();
							} else {
								vn[i-1] = 0;
							}
						}
						faces.add(v);
						facestexs.add(vt);
						facesnorms.add(vn);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	public float getXWidth() {
		float returnval = 0;
		returnval = rightpoint - leftpoint;
		return returnval;
	}
	public float getYHeight() {
		float returnval = 0;
		returnval = toppoint - bottompoint;
		return returnval;
	}
	public float getZDepth() {
		float returnval = 0;
		returnval = nearpoint - farpoint;
		return returnval;
	}
	public int numpolygons() {
		return numpolys;
	}
	public void prepareDraw() {
		this.objectlist = GL11.glGenLists(1);
		GL11.glNewList(objectlist,GL11.GL_COMPILE);
		for (int i=0;i<faces.size();i++) {
			int[] tempfaces = faces.get(i);
			int[] tempfacesnorms = facesnorms.get(i);
			int[] tempfacestexs = facestexs.get(i);
			int polytype;
			if (tempfaces.length == 3) {
				polytype = GL11.GL_TRIANGLES;
			} else if (tempfaces.length == 4) {
				polytype = GL11.GL_QUADS;
			} else {
	polytype = GL11.GL_POLYGON;
	}
			GL11.glBegin(polytype);
			for (int w=0;w<tempfaces.length;w++) {
				if (tempfacesnorms[w] != 0) {
					float normtempx = ((float[])vertexsetsnorms.get(tempfacesnorms[w] - 1))[0];
					float normtempy = ((float[])vertexsetsnorms.get(tempfacesnorms[w] - 1))[1];
					float normtempz = ((float[])vertexsetsnorms.get(tempfacesnorms[w] - 1))[2];
					GL11.glNormal3f(normtempx, normtempy, normtempz);
				}
				if (tempfacestexs[w] != 0) {;
					float textempx = vertexsetstexs.get(tempfacestexs[w] - 1)[0];
					float textempy = vertexsetstexs.get(tempfacestexs[w] - 1)[1];
					float textempz = vertexsetstexs.get(tempfacestexs[w] - 1)[2];
					GL11.glTexCoord3f(textempx,1f-textempy,textempz);
				}
				float tempx = vertexsets.get(tempfaces[w] - 1)[0];
				float tempy = vertexsets.get(tempfaces[w] - 1)[1];
				float tempz = vertexsets.get(tempfaces[w] - 1)[2];
				GL11.glVertex3f(tempx,tempy,tempz);
			}
			GL11.glEnd();
		}
		GL11.glEndList();
	}
	public void draw() {
		GL11.glCallList(objectlist);
	}
}