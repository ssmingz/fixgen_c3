class PlaceHold {
  public File getBaseDir() {
    if (baseDir == null) {
      try {
        setBasedir(".");
      } catch (final BuildException ex) {
        ex.printStackTrace();
      }
    }
    return baseDir;
  }
}
