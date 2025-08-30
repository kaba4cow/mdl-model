package com.kaba4cow.mdlmodel;

import java.io.IOException;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a single frame of animation in an {@code MDL} model. Each frame contains a bounding box (min/max vertices), a
 * name, and an array of vertices defining the model's geometry for this frame.
 */
public class MDLFrame {

	private final MDLVertex min;
	private final MDLVertex max;
	private final String name;
	private final MDLVertex[] vertices;

	MDLFrame(int numVertices, BinaryReader reader) throws IOException {
		this.min = new MDLVertex(reader);
		this.max = new MDLVertex(reader);
		this.name = reader.readStringFixed(16).split("\0")[0];
		this.vertices = new MDLVertex[numVertices];
		for (int i = 0; i < numVertices; i++)
			this.vertices[i] = new MDLVertex(reader);
	}

	/**
	 * Gets the minimum vertex of the frame's bounding box.
	 * 
	 * @return the vertex representing the minimum bounds
	 */
	public MDLVertex getMin() {
		return this.min;
	}

	/**
	 * Gets the maximum vertex of the frame's bounding box.
	 * 
	 * @return the vertex representing the maximum bounds
	 */
	public MDLVertex getMax() {
		return this.max;
	}

	/**
	 * Gets the name of this frame.
	 * 
	 * @return the frame name as a {@link String}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the array of vertices that define this frame's geometry.
	 * 
	 * @return an array of {@link MDLVertex} objects
	 */
	public MDLVertex[] getVertices() {
		return this.vertices;
	}

	@Override
	public String toString() {
		return String.format("MDLFrame [min=%s, max=%s, name=%s]", this.min, this.max, this.name);
	}

}
