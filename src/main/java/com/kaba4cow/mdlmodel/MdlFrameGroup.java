package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a group of animation frames in an {@code MDL} model. A frame group can either be a single frame or multiple frames
 * with associated timing information.
 */
public class MdlFrameGroup {

	private final MdlVertex min;
	private final MdlVertex max;
	private final MdlFrame[] frames;
	private final float[] times;

	MdlFrameGroup(int numVertices, BinaryReader reader) throws IOException {
		if (reader.readInt() == 0) {
			this.min = null;
			this.max = null;
			this.frames = new MdlFrame[] { new MdlFrame(numVertices, reader) };
			this.times = new float[] { 0.0f };
		} else {
			int numFrames = reader.readInt();
			this.min = new MdlVertex(reader);
			this.max = new MdlVertex(reader);
			this.frames = new MdlFrame[numFrames];
			this.times = reader.readFloatArray(numFrames);
			for (int i = 0; i < numFrames; i++)
				this.frames[i] = new MdlFrame(numVertices, reader);
		}
	}

	/**
	 * Gets the minimum vertex of the frame group's bounding box.
	 * 
	 * @return the {@link MdlVertex} representing the minimum bounds, or {@code null} for single-frame groups
	 */
	public MdlVertex getMin() {
		return this.min;
	}

	/**
	 * Gets the maximum vertex of the frame group's bounding box.
	 * 
	 * @return the {@link MdlVertex} representing the maximum bounds, or {@code null} for single-frame groups
	 */
	public MdlVertex getMax() {
		return this.max;
	}

	/**
	 * Gets the array of frames in this group.
	 * 
	 * @return an array of {@link MdlFrame} objects
	 */
	public MdlFrame[] getFrames() {
		return this.frames;
	}

	/**
	 * Gets the timing information for each frame in the group.
	 * 
	 * @return an array of float values representing the time for each frame
	 */
	public float[] getTimes() {
		return this.times;
	}

	@Override
	public String toString() {
		return String.format("MdlFrameGroup [min=%s, max=%s, frames=%s, times=%s]", this.min, this.max, Arrays.toString(this.frames),
				Arrays.toString(this.times));
	}

}
