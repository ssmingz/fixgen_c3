class PlaceHold {
  private static File normalizeSource(File source) {
    if (source != null) {
      FileUtils fileUtils = FileUtils.newFileUtils();
      try {
        source = fileUtils.normalize(source.getAbsolutePath());
      } catch (BuildException e) {
      }
    }
    return source;
  }
}
