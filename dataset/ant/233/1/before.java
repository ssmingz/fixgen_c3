class PlaceHold {
  public File getBaseDir() {
    if (baseDir == null) {
      try {
        setBasedir(".");
      } catch (BuildException ex) {
        ex.printStackTrace();
      }
    }
    return baseDir;
  }
}
