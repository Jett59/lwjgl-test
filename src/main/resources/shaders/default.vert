layout(location=0) in vec4 position;
layout (location=1) in vec2 texCoord;

out vec2 outTexCoord;

uniform int cameraZoom;
uniform int translateX;
uniform int translateY;
uniform int translateZ;

void main(){
	gl_Position = gl_ModelViewProjectionMatrix*position;
	gl_Position = vec4(gl_Position.xy*cameraZoom, gl_Position.zw);
	gl_Position = vec4(gl_Position.x+translateX, gl_Position.y+translateY, gl_Position.z+translateZ, gl_Position.w);
	outTexCoord = texCoord;
}