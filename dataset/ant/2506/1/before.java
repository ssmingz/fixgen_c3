class PlaceHold {
  protected void validate() throws BuildException {
    if ((outputFile != null) && outputFile.isDirectory()) {
      throw new BuildException("destFile cannot be a directory");
    }
  }
}
