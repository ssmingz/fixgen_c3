class PlaceHold {
  protected void checkConfiguration() throws BuildException {
    if (cmdl.getExecutable() == null) {
      throw new BuildException("no executable specified");
    }
    if ((dir != null) && (!dir.exists())) {
      throw new BuildException("The directory you specified does not exist");
    }
    if ((dir != null) && (!dir.isDirectory())) {
      throw new BuildException("The directory you specified is not a directory");
    }
  }
}
