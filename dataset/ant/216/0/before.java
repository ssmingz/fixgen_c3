class PlaceHold {
  private void ensureDirectoryFor(File targetFile) throws BuildException {
    File directory = new File(targetFile.getParent());
    if (!directory.exists()) {
      if (!directory.mkdirs()) {
        throw new BuildException("Unable to create directory: " + directory.getAbsolutePath());
      }
    }
  }
}
