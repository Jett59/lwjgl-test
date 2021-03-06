layout(location=0) in vec4 position;
layout (location=1) in vec2 texCoord;

out vec2 outTexCoord;
in vec3 vertex;

uniform float cameraZoom;
uniform float translateX;
uniform float translateY;
uniform float translateZ;

void main(){
	gl_Position = vec4(vertex, 1.0);
	gl_Position = vec4(gl_Position.xyz+position.xyz, min(1.0, gl_Position.w+position.w));
	gl_Position = vec4(gl_Position.x+translateX, gl_Position.y+translateY, gl_Position.z+translateZ, gl_Position.w);
	gl_Position = vec4(gl_Position.xy*cameraZoom, gl_Position.zw);
	gl_Position = gl_ModelViewProjectionMatrix*gl_Position;
	outTexCoord = texCoord;
}