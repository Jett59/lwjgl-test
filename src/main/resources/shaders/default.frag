in  vec2 outTexCoord;
out vec4 fragColor;

uniform sampler2D texture_sampler;

void main(){
    fragColor = texture(texture_sampler, outTexCoord);
    fragColor = vec4(0.0, 0.0, 1.0, 1.0);
}