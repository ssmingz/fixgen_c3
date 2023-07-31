class PlaceHold {
  private void ensureDirectoryFor(File targetFile) throws BuildException {
    File directory = fileUtils.getParentFile(targetFile);
    if (!directory.exists()) {
      if (!directory.mkdirs()) {
        throw new BuildException("Unable to create directory: " + directory.getAbsolutePath());
      }
    }
  }
}
