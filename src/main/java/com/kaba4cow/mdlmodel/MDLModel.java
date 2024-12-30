package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.io.InputStream;

import com.kaba4cow.binprocessor.enums.ByteOrder;
import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a complete {@code MDL} model file. This is the root class for accessing all model data including geometry,
 * textures, animations, and transformation information.
 */
public class MDLModel {

	private final MDLVector scale;
	private final MDLVector translation;
	private final MDLVector eyePosition;

	private final float boundingRadius;
	private final float size;

	private final int syncType;
	private final int flags;

	private final int textureWidth;
	private final int textureHeight;

	private final int numTextureGroups;
	private final MDLTextureGroup[] textureGroups;

	private final int numVertices;
	private final MDLTextureCoord[] textureCoords;

	private final int numTriangles;
	private final MDLTriangle[] triangles;

	private final int numFrameGroups;
	private final MDLFrameGroup[] frameGroups;

	/**
	 * Constructs a new {@code MDLModel} by reading model data from an input stream. Validates the file format identifier
	 * {@code IDPO} and version number {@code 6}.
	 * 
	 * @param input the {@link InputStream} containing MDL model data
	 * 
	 * @throws IOException              if an I/O error occurs
	 * @throws IllegalArgumentException if the file identifier is not "IDPO" or version is not 6
	 */
	public MDLModel(InputStream input) throws IOException {
		try (BinaryReader reader = new BinaryReader(input)) {
			reader.setOrder(ByteOrder.LITTLE_ENDIAN);
			String identifier = reader.readString(4);
			if (!"IDPO".equals(identifier))
				throw new IllegalArgumentException(String.format("Unsupported identifier: %s", identifier));
			int version = reader.readInt();
			if (version != 6)
				throw new IllegalArgumentException(String.format("Unsupported version: %s", version));

			this.scale = new MDLVector(reader);
			this.translation = new MDLVector(reader);
			this.boundingRadius = reader.readFloat();
			this.eyePosition = new MDLVector(reader);

			this.numTextureGroups = reader.readInt();
			this.textureWidth = reader.readInt();
			this.textureHeight = reader.readInt();

			this.numVertices = reader.readInt();
			this.numTriangles = reader.readInt();
			this.numFrameGroups = reader.readInt();

			this.syncType = reader.readInt();
			this.flags = reader.readInt();
			this.size = reader.readFloat();

			this.textureGroups = new MDLTextureGroup[numTextureGroups];
			for (int i = 0; i < numTextureGroups; i++)
				this.textureGroups[i] = new MDLTextureGroup(textureWidth, textureHeight, reader);
			this.textureCoords = new MDLTextureCoord[numVertices];
			for (int i = 0; i < numVertices; i++)
				this.textureCoords[i] = new MDLTextureCoord(reader);
			this.triangles = new MDLTriangle[numTriangles];
			for (int i = 0; i < numTriangles; i++)
				this.triangles[i] = new MDLTriangle(reader);
			this.frameGroups = new MDLFrameGroup[numFrameGroups];
			for (int i = 0; i < numFrameGroups; i++)
				this.frameGroups[i] = new MDLFrameGroup(numVertices, reader);
		}
	}

	/**
	 * Gets the model's scale vector.
	 * 
	 * @return the {@link MDLVector} representing scale
	 */
	public MDLVector getScale() {
		return scale;
	}

	/**
	 * Gets the model's translation vector.
	 * 
	 * @return the {@link MDLVector} representing translation
	 */
	public MDLVector getTranslation() {
		return translation;
	}

	/**
	 * Gets the model's eye position vector.
	 * 
	 * @return the {@link MDLVector} representing the eye position
	 */
	public MDLVector getEyePosition() {
		return eyePosition;
	}

	/**
	 * Gets the model's bounding radius.
	 * 
	 * @return the bounding radius
	 */
	public float getBoundingRadius() {
		return boundingRadius;
	}

	/**
	 * Gets the model's size value.
	 * 
	 * @return the size
	 */
	public float getSize() {
		return size;
	}

	/**
	 * Gets the model's sync type.
	 * 
	 * @return the sync type
	 */
	public int getSyncType() {
		return syncType;
	}

	/**
	 * Gets the model's flags.
	 * 
	 * @return the flags
	 */
	public int getFlags() {
		return flags;
	}

	/**
	 * Gets the width of the model's textures.
	 * 
	 * @return the texture width
	 */
	public int getTextureWidth() {
		return textureWidth;
	}

	/**
	 * Gets the height of the model's textures.
	 * 
	 * @return the texture height
	 */
	public int getTextureHeight() {
		return textureHeight;
	}

	/**
	 * Gets the number of texture groups in the model.
	 * 
	 * @return the number of texture groups
	 */
	public int getNumTextureGroups() {
		return numTextureGroups;
	}

	/**
	 * Gets all texture groups in the model.
	 * 
	 * @return an array of {@link MDLTextureGroup} objects
	 */
	public MDLTextureGroup[] getTextureGroups() {
		return textureGroups;
	}

	/**
	 * Gets the total number of vertices in the model.
	 * 
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return numVertices;
	}

	/**
	 * Gets the texture coordinates for all vertices.
	 * 
	 * @return an array of {@link MDLTextureCoord} objects mapping vertices to texture coordinates
	 */
	public MDLTextureCoord[] getTextureCoords() {
		return textureCoords;
	}

	/**
	 * Gets the number of triangles in the model.
	 * 
	 * @return the number of triangles
	 */
	public int getNumTriangles() {
		return numTriangles;
	}

	/**
	 * Gets all triangles that make up the model's geometry.
	 * 
	 * @return an array of {@link MDLTriangle} objects defining the model's faces
	 */
	public MDLTriangle[] getTriangles() {
		return triangles;
	}

	/**
	 * Gets the number of frame groups in the model.
	 * 
	 * @return the number of frame groups
	 */
	public int getNumFrameGroups() {
		return numFrameGroups;
	}

	/**
	 * Gets all frame groups in the model.
	 * 
	 * @return an array of {@link MDLFrameGroup} objects
	 */
	public MDLFrameGroup[] getFrameGroups() {
		return frameGroups;
	}

}
