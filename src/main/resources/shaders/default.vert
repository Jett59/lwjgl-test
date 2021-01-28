layout (location=1) in vec2 texCoord;

out vec2 outTexCoord;

void main(){
	gl_Position = gl_ModelViewProjectionMatrix*gl_Vertex;
	outTexCoord = texCoord;
}