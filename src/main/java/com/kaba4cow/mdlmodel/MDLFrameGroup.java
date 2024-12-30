package com.kaba4cow.mdlmodel;

import java.io.IOException;
import java.util.Arrays;

import com.kaba4cow.binprocessor.io.BinaryReader;

/**
 * Represents a group of animation frames in an {@code MDL} model. A frame group can either be a single frame or multiple frames
 * with associated timing information.
 */
public class MDLFrameGroup {

	private final MDLVertex min;
	private final MDLVertex max;
	private final MDLFrame[] frames;
	private final float[] times;

	MDLFrameGroup(int numVertices, BinaryReader reader) throws IOException {
		if (reader.readInt() == 0) {
			this.min = null;
			this.max = null;
			this.frames = new MDLFrame[] { new MDLFrame(numVertices, reader) };
			this.times = new float[] { 0.0f };
		} else {
			int numFrames = reader.readInt();
			this.min = new MDLVertex(reader);
			this.max = new MDLVertex(reader);
			this.frames = new MDLFrame[numFrames];
			this.times = reader.readFloatArray(numFrames);
			for (int i = 0; i < numFrames; i++)
				this.frames[i] = new MDLFrame(numVertices, reader);
		}
	}

	/**
	 * Gets the minimum vertex of the frame group's bounding box.
	 * 
	 * @return the {@link MDLVertex} representing the minimum bounds, or {@code null} for single-frame groups
	 */
	public MDLVertex getMin() {
		return min;
	}

	/**
	 * Gets the maximum vertex of the frame group's bounding box.
	 * 
	 * @return the {@link MDLVertex} representing the maximum bounds, or {@code null} for single-frame groups
	 */
	public MDLVertex getMax() {
		return max;
	}

	/**
	 * Gets the array of frames in this group.
	 * 
	 * @return an array of {@link MDLFrame} objects
	 */
	public MDLFrame[] getFrames() {
		return frames;
	}

	/**
	 * Gets the timing information for each frame in the group.
	 * 
	 * @return an array of float values representing the time for each frame
	 */
	public float[] getTimes() {
		return times;
	}

	@Override
	public String toString() {
		return String.format("MDLFrameGroup [min=%s, max=%s, frames=%s, times=%s]", min, max, Arrays.toString(frames),
				Arrays.toString(times));
	}

}
