class PlaceHold {
  public File createTempFile(String prefix, String suffix, File parentDir, boolean deleteOnExit) {
    return createTempFile(prefix, suffix, parentDir, deleteOnExit, false);
  }
}
