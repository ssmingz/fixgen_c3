class PlaceHold {
  public void processFile(File file, File newFile) {
    try {
      log("Processing File: " + file.getAbsolutePath());
      FileSeekableStream input = null;
      PlanarImage image = null;
      try {
        input = new FileSeekableStream(file);
        image = JAI.create("stream", input);
        for (int i = 0; i < instructions.size(); i++) {
          Object instr = instructions.elementAt(i);
          if (instr instanceof TransformOperation) {
            image = ((TransformOperation) (instr)).executeTransformOperation(image);
          } else {
            log("Not a TransformOperation: " + instr);
          }
        }
      } finally {
        FileUtils.close(input);
      }
      File dstParent = newFile.getParentFile();
      if ((!dstParent.isDirectory()) && (!dstParent.mkdirs())) {
        throw new BuildException("Failed to create parent directory " + dstParent);
      }
      if ((overwrite && newFile.exists()) && (!newFile.equals(file))) {
        newFile.delete();
      }
      FileOutputStream stream = null;
      try {
        stream = new FileOutputStream(newFile);
        JAI.create("encode", image, stream, str_encoding.toUpperCase(Locale.ENGLISH), null);
        stream.flush();
      } finally {
        FileUtils.close(stream);
      }
    } catch (IOException err) {
      if (!file.equals(newFile)) {
        newFile.delete();
      }
      if (!failonerror) {
        log("Error processing file:  " + err);
      } else {
        throw new BuildException(err);
      }
    } catch (RuntimeException rerr) {
      if (!file.equals(newFile)) {
        newFile.delete();
      }
      if (!failonerror) {
        log("Error processing file:  " + rerr);
      } else {
        throw new BuildException(rerr);
      }
    }
  }
}
