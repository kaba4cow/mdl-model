package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a group of textures in an {@code MDL} model. A texture group can either be a single texture or multiple textures
 * with associated timing information for animated textures.
 */
public class MdlTextureGroup {

	private final MdlTexture[] textures;
	private final float[] times;

	MdlTextureGroup(int width, int height, BinaryReader reader) throws IOException {
		if (reader.readInt() == 0) {
			this.textures = new MdlTexture[] { new MdlTexture(width, height, reader) };
			this.times = new float[] { 0.0f };
		} else {
			int numTextures = reader.readInt();
			this.textures = new MdlTexture[numTextures];
			this.times = reader.readFloatArray(numTextures);
			for (int i = 0; i < numTextures; i++)
				this.textures[i] = new MdlTexture(width, height, reader);
		}
	}

	/**
	 * Gets the array of textures in this group.
	 * 
	 * @return an array of {@link MdlTexture} objects
	 */
	public MdlTexture[] getTextures() {
		return this.textures;
	}

	/**
	 * Gets the timing information for each texture in the group.
	 * 
	 * @return an array of float values representing the time for each texture
	 */
	public float[] getTimes() {
		return this.times;
	}

	@Override
	public String toString() {
		return String.format("MdlTextureGroup [textures=%s, times=%s]", Arrays.toString(this.textures), Arrays.toString(this.times));
	}

}
