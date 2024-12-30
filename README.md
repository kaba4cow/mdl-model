# MDL Model Library

A Java library for reading **MDL** model files. This library provides an immutable object model for working with **MDL** files, making it easy to load and access 3D model data including geometry, textures, animations, and transformations.

**Note:** the library depends on the [bin-processor](https://github.com/kaba4cow/bin-processor) library for low-level binary file reading.

## File Format Support

The library supports files with:
- File identifier: **IDPO**
- Version: **6**
- Little-endian byte order

## Usage

### Loading a Model

```java
try (InputStream input = new FileInputStream("model.mdl")) {
    MDLModel model = new MDLModel(input);
    // Work with the model...
}
```

### Accessing Model Data

```java
// Get basic model properties
MDLVector scale = model.getScale();
MDLVector translation = model.getTranslation();
float boundingRadius = model.getBoundingRadius();

// Access geometry
MDLTriangle[] triangles = model.getTriangles();
MDLVertex[] vertices = model.getFrameGroups()[0].getFrames()[0].getVertices();

// Work with textures
MDLTextureGroup[] textureGroups = model.getTextureGroups();
MDLTextureCoord[] textureCoords = model.getTextureCoords();

// Access animations
MDLFrameGroup[] frameGroups = model.getFrameGroups();
```

## Class Overview

- `MDLModel`: root class representing the complete model file
- `MDLFrame`: single frame of animation with vertex data
- `MDLFrameGroup`: collection of animation frames with timing
- `MDLTexture`: individual texture with indexed color data
- `MDLTextureGroup`: collection of textures with animation timing
- `MDLTextureCoord`: UV coordinates for texture mapping
- `MDLTriangle`: three vertex indices forming a triangle face
- `MDLVertex`: 3D vertex with position and normal index
- `MDLVector`: 3D vector for transforms and coordinates
- `MDLNormals`: static collection of predefined normal vectors from **Quake I**

## Error Handling

- `IOException` for I/O errors
- `IllegalArgumentException` for unsupported file versions or identifiers