package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.io.InputStream;

import com.kaba4cow.binprocessor.enums.ByteOrder;
import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a complete {@code MDL} model file. This is the root class for accessing all model data including geometry,
 * textures, animations, and transformation information.
 */
public class MdlModel {

	private final MdlVector scale;
	private final MdlVector translation;
	private final MdlVector eyePosition;

	private final float boundingRadius;
	private final float size;

	private final int syncType;
	private final int flags;

	private final int textureWidth;
	private final int textureHeight;

	private final int numTextureGroups;
	private final MdlTextureGroup[] textureGroups;

	private final int numVertices;
	private final MdlTextureCoord[] textureCoords;

	private final int numTriangles;
	private final MdlTriangle[] triangles;

	private final int numFrameGroups;
	private final MdlFrameGroup[] frameGroups;

	/**
	 * Constructs a new {@code MDLModel} by reading model data from an input stream. Validates the file format identifier
	 * {@code IDPO} and version number {@code 6}.
	 * 
	 * @param input the {@link InputStream} containing MDL model data
	 * 
	 * @throws IOException              if an I/O error occurs
	 * @throws IllegalArgumentException if the file identifier is not "IDPO" or version is not 6
	 */
	public MdlModel(InputStream input) throws IOException {
		try (BinaryReader reader = new BinaryReader(input)) {
			reader.setOrder(ByteOrder.LITTLE_ENDIAN);
			String identifier = reader.readStringFixed(4);
			if (!"IDPO".equals(identifier))
				throw new IllegalArgumentException(String.format("Unsupported identifier: %s", identifier));
			int version = reader.readInt();
			if (version != 6)
				throw new IllegalArgumentException(String.format("Unsupported version: %s", version));

			this.scale = new MdlVector(reader);
			this.translation = new MdlVector(reader);
			this.boundingRadius = reader.readFloat();
			this.eyePosition = new MdlVector(reader);

			this.numTextureGroups = reader.readInt();
			this.textureWidth = reader.readInt();
			this.textureHeight = reader.readInt();

			this.numVertices = reader.readInt();
			this.numTriangles = reader.readInt();
			this.numFrameGroups = reader.readInt();

			this.syncType = reader.readInt();
			this.flags = reader.readInt();
			this.size = reader.readFloat();

			this.textureGroups = new MdlTextureGroup[this.numTextureGroups];
			for (int i = 0; i < this.numTextureGroups; i++)
				this.textureGroups[i] = new MdlTextureGroup(this.textureWidth, this.textureHeight, reader);
			this.textureCoords = new MdlTextureCoord[this.numVertices];
			for (int i = 0; i < this.numVertices; i++)
				this.textureCoords[i] = new MdlTextureCoord(reader);
			this.triangles = new MdlTriangle[this.numTriangles];
			for (int i = 0; i < this.numTriangles; i++)
				this.triangles[i] = new MdlTriangle(reader);
			this.frameGroups = new MdlFrameGroup[this.numFrameGroups];
			for (int i = 0; i < this.numFrameGroups; i++)
				this.frameGroups[i] = new MdlFrameGroup(this.numVertices, reader);
		}
	}

	/**
	 * Gets the model's scale vector.
	 * 
	 * @return the {@link MdlVector} representing scale
	 */
	public MdlVector getScale() {
		return this.scale;
	}

	/**
	 * Gets the model's translation vector.
	 * 
	 * @return the {@link MdlVector} representing translation
	 */
	public MdlVector getTranslation() {
		return this.translation;
	}

	/**
	 * Gets the model's eye position vector.
	 * 
	 * @return the {@link MdlVector} representing the eye position
	 */
	public MdlVector getEyePosition() {
		return this.eyePosition;
	}

	/**
	 * Gets the model's bounding radius.
	 * 
	 * @return the bounding radius
	 */
	public float getBoundingRadius() {
		return this.boundingRadius;
	}

	/**
	 * Gets the model's size value.
	 * 
	 * @return the size
	 */
	public float getSize() {
		return this.size;
	}

	/**
	 * Gets the model's sync type.
	 * 
	 * @return the sync type
	 */
	public int getSyncType() {
		return this.syncType;
	}

	/**
	 * Gets the model's flags.
	 * 
	 * @return the flags
	 */
	public int getFlags() {
		return this.flags;
	}

	/**
	 * Gets the width of the model's textures.
	 * 
	 * @return the texture width
	 */
	public int getTextureWidth() {
		return this.textureWidth;
	}

	/**
	 * Gets the height of the model's textures.
	 * 
	 * @return the texture height
	 */
	public int getTextureHeight() {
		return this.textureHeight;
	}

	/**
	 * Gets the number of texture groups in the model.
	 * 
	 * @return the number of texture groups
	 */
	public int getNumTextureGroups() {
		return this.numTextureGroups;
	}

	/**
	 * Gets all texture groups in the model.
	 * 
	 * @return an array of {@link MdlTextureGroup} objects
	 */
	public MdlTextureGroup[] getTextureGroups() {
		return this.textureGroups;
	}

	/**
	 * Gets the total number of vertices in the model.
	 * 
	 * @return the number of vertices
	 */
	public int getNumVertices() {
		return this.numVertices;
	}

	/**
	 * Gets the texture coordinates for all vertices.
	 * 
	 * @return an array of {@link MdlTextureCoord} objects mapping vertices to texture coordinates
	 */
	public MdlTextureCoord[] getTextureCoords() {
		return this.textureCoords;
	}

	/**
	 * Gets the number of triangles in the model.
	 * 
	 * @return the number of triangles
	 */
	public int getNumTriangles() {
		return this.numTriangles;
	}

	/**
	 * Gets all triangles that make up the model's geometry.
	 * 
	 * @return an array of {@link MdlTriangle} objects defining the model's faces
	 */
	public MdlTriangle[] getTriangles() {
		return this.triangles;
	}

	/**
	 * Gets the number of frame groups in the model.
	 * 
	 * @return the number of frame groups
	 */
	public int getNumFrameGroups() {
		return this.numFrameGroups;
	}

	/**
	 * Gets all frame groups in the model.
	 * 
	 * @return an array of {@link MdlFrameGroup} objects
	 */
	public MdlFrameGroup[] getFrameGroups() {
		return this.frameGroups;
	}

}
