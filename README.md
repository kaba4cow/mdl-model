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
    MdlModel model = new MdlModel(input);
    // Work with the model...
}
```

### Accessing Model Data

```java
// Get basic model properties
MdlVector scale = model.getScale();
MdlVector translation = model.getTranslation();
float boundingRadius = model.getBoundingRadius();

// Access geometry
MdlTriangle[] triangles = model.getTriangles();
MdlVertex[] vertices = model.getFrameGroups()[0].getFrames()[0].getVertices();

// Work with textures
MdlTextureGroup[] textureGroups = model.getTextureGroups();
MdlTextureCoord[] textureCoords = model.getTextureCoords();

// Access animations
MdlFrameGroup[] frameGroups = model.getFrameGroups();
```

## Class Overview

- `MdlModel`: root class representing the complete model file
- `MdlFrame`: single frame of animation with vertex data
- `MdlFrameGroup`: collection of animation frames with timing
- `MdlTexture`: individual texture with indexed color data
- `MdlTextureGroup`: collection of textures with animation timing
- `MdlTextureCoord`: UV coordinates for texture mapping
- `MdlTriangle`: three vertex indices forming a triangle face
- `MdlVertex`: 3D vertex with position and normal index
- `MdlVector`: 3D vector for transforms and coordinates
- `MdlNormals`: static collection of predefined normal vectors from **Quake I**

## Error Handling

- `IOException` for I/O errors
- `IllegalArgumentException` for unsupported file versions or identifiers

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.