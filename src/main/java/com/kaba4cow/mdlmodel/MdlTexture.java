package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a texture in an {@code MDL} model. The texture is stored as an indexed image with a specific width and height,
 * where each pixel is represented by an index into a color palette.
 */
public class MdlTexture {

	private final int width;
	private final int height;
	private final byte[] indices;

	MdlTexture(int width, int height, BinaryReader reader) throws IOException {
		this.width = width;
		this.height = height;
		this.indices = reader.readByteArray(width * height);
	}

	/**
	 * Gets the width of the texture.
	 * 
	 * @return the width in pixels
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Gets the height of the texture.
	 * 
	 * @return the height in pixels
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the array of texture indices.
	 * 
	 * @return a byte array containing the palette indices for each pixel
	 */
	public byte[] getIndices() {
		return this.indices;
	}

	@Override
	public String toString() {
		return String.format("MdlTexture [width=%s, height=%s, indices=%s]", this.width, this.height, Arrays.toString(this.indices));
	}

}
