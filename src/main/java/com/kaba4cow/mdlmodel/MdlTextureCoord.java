package com.kaba4cow.mdlmodel;

import java.io.IOException;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents texture coordinates for a vertex in an {@code MDL} model. Contains S and T coordinates for UV mapping and a seam
 * flag.
 */
public class MdlTextureCoord {

	private final boolean seam;
	private final int s;
	private final int t;

	MdlTextureCoord(BinaryReader reader) throws IOException {
		this.seam = reader.readInt() != 0;
		this.s = reader.readInt();
		this.t = reader.readInt();
	}

	/**
	 * Checks if this texture coordinate is on a texture seam.
	 * 
	 * @return {@code true} if this coordinate is on a seam, {@code false} otherwise
	 */
	public boolean isSeam() {
		return this.seam;
	}

	/**
	 * Gets the S (U) texture coordinate.
	 * 
	 * @return the S coordinate
	 */
	public int getS() {
		return this.s;
	}

	/**
	 * Gets the T (V) texture coordinate.
	 * 
	 * @return the T coordinate
	 */
	public int getT() {
		return this.t;
	}

	@Override
	public String toString() {
		return String.format("MdlTextureCoord [seam=%s, s=%s, t=%s]", this.seam, this.s, this.t);
	}

}
