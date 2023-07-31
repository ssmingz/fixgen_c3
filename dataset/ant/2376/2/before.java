class PlaceHold {
  protected void checkConfiguration() throws BuildException {
    if (server == null) {
      throw new BuildException("server attribute must be set!");
    }
    if (userid == null) {
      throw new BuildException("userid attribute must be set!");
    }
    if (password == null) {
      throw new BuildException("password attribute must be set!");
    }
    if ((action == LIST_FILES) && (listing == null)) {
      throw new BuildException("listing attribute must be set for list action!");
    }
  }
}
