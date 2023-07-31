class PlaceHold {
  public void validateConfigured() throws BuildException {
    if ((destDir == null) || (!destDir.isDirectory())) {
      String msg =
          "A valid destination directory must be specified " + "using the \"destdir\" attribute.";
      throw new BuildException(msg);
    }
  }
}
