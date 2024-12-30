package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a group of textures in an {@code MDL} model. A texture group can either be a single texture or multiple textures
 * with associated timing information for animated textures.
 */
public class MDLTextureGroup {

	private final MDLTexture[] textures;
	private final float[] times;

	MDLTextureGroup(int width, int height, BinaryReader reader) throws IOException {
		if (reader.readInt() == 0) {
			this.textures = new MDLTexture[] { new MDLTexture(width, height, reader) };
			this.times = new float[] { 0.0f };
		} else {
			int numTextures = reader.readInt();
			this.textures = new MDLTexture[numTextures];
			this.times = reader.readFloatArray(numTextures);
			for (int i = 0; i < numTextures; i++)
				this.textures[i] = new MDLTexture(width, height, reader);
		}
	}

	/**
	 * Gets the array of textures in this group.
	 * 
	 * @return an array of {@link MDLTexture} objects
	 */
	public MDLTexture[] getTextures() {
		return textures;
	}

	/**
	 * Gets the timing information for each texture in the group.
	 * 
	 * @return an array of float values representing the time for each texture
	 */
	public float[] getTimes() {
		return times;
	}

	@Override
	public String toString() {
		return String.format("MDLTextureGroup [textures=%s, times=%s]", Arrays.toString(textures), Arrays.toString(times));
	}

}
