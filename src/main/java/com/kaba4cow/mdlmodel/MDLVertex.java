package com.kaba4cow.mdlmodel;

import java.io.IOException;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a vertex in an {@code MDL} model. Each vertex contains integer coordinates and a normal vector index. The
 * coordinates are stored as unsigned bytes (0-255).
 */
public class MDLVertex {

	private final int x;
	private final int y;
	private final int z;
	private final int normal;

	MDLVertex(BinaryReader reader) throws IOException {
		this.x = reader.readByte() & 0xFF;
		this.y = reader.readByte() & 0xFF;
		this.z = reader.readByte() & 0xFF;
		this.normal = reader.readByte() & 0xFF;
	}

	/**
	 * Gets the X coordinate of the vertex.
	 * 
	 * @return the X coordinate as an integer (0-255)
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the Y coordinate of the vertex.
	 * 
	 * @return the Y coordinate as an integer (0-255)
	 */
	public int getY() {
		return y;
	}

	/**
	 * Gets the Z coordinate of the vertex.
	 * 
	 * @return the Z coordinate as an integer (0-255)
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Gets the index of this vertex's normal vector in the {@link MDLNormals} collection.
	 * 
	 * @return the normal vector index as an integer (0-255)
	 */
	public int getNormal() {
		return normal;
	}

	@Override
	public String toString() {
		return String.format("MDLVertex [x=%s, y=%s, z=%s, normal=%s]", x, y, z, normal);
	}

}
