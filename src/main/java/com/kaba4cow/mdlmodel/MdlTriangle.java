package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a triangle in an {@code MDL} model. Each triangle consists of three vertex indices and a front-facing flag.
 */
public class MdlTriangle {

	private final boolean front;
	private final int[] vertices;

	MdlTriangle(BinaryReader reader) throws IOException {
		this.front = reader.readInt() != 0;
		this.vertices = reader.readIntArray(3);
	}

	/**
	 * Checks if this triangle is front-facing.
	 * 
	 * @return {@code true} if the triangle is front-facing, {@code false} otherwise
	 */
	public boolean isFront() {
		return this.front;
	}

	/**
	 * Gets the vertex indices that make up this triangle.
	 * 
	 * @return an array of three integers representing vertex indices
	 */
	public int[] getVertices() {
		return this.vertices;
	}

	@Override
	public String toString() {
		return String.format("MdlTriangle [front=%s, vertices=%s]", this.front, Arrays.toString(this.vertices));
	}

}
