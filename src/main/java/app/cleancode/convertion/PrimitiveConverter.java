package app.cleancode.convertion;

public class PrimitiveConverter {
public static int fromFloat(float f) {
	int result = 0;
	for(int bit = 0; bit < Float.SIZE; bit++) {
		int mask = 1 << bit;
		result = result | (result & mask);
	}
	return result;
}
}
