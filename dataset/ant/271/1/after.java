class PlaceHold {
  private static File normalizeSource(File source) {
    if (source != null) {
      try {
        source = FILE_UTILS.normalize(source.getAbsolutePath());
      } catch (BuildException e) {
      }
    }
    return source;
  }
}
