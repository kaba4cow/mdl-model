package com.kaba4cow.mdlmodel;

import java.io.IOException;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a 3D vector in an {@code MDL} model. This class is used for storing coordinates and directions in 3D space using
 * floating-point precision.
 */
public class MDLVector {

	private final float x;
	private final float y;
	private final float z;

	MDLVector(BinaryReader reader) throws IOException {
		this.x = reader.readFloat();
		this.y = reader.readFloat();
		this.z = reader.readFloat();
	}

	MDLVector(float[] array) {
		this.x = array[0];
		this.y = array[1];
		this.z = array[2];
	}

	/**
	 * Gets the X component of the vector.
	 * 
	 * @return the X coordinate as a float
	 */
	public float getX() {
		return x;
	}

	/**
	 * Gets the Y component of the vector.
	 * 
	 * @return the Y coordinate as a float
	 */
	public float getY() {
		return y;
	}

	/**
	 * Gets the Z component of the vector.
	 * 
	 * @return the Z coordinate as a float
	 */
	public float getZ() {
		return z;
	}

	@Override
	public String toString() {
		return String.format("MDLVector [x=%s, y=%s, z=%s]", x, y, z);
	}

}
