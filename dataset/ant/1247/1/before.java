class PlaceHold {
  public File createTempFile(String prefix, String suffix, File parentDir, boolean deleteOnExit) {
    File result = null;
    String parent =
        (parentDir == null) ? System.getProperty("java.io.tmpdir") : parentDir.getPath();
    try {
      result = File.createTempFile(prefix, suffix, new File(parent));
    } catch (IOException e) {
      throw new BuildException("Could not create tempfile in " + parent, e);
    }
    if (deleteOnExit) {
      result.deleteOnExit();
    }
    return result;
  }
}
