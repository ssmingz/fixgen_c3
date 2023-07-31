class PlaceHold {
  public void execute() throws BuildException {
    if ((token == null) || (value == null)) {
      throw new BuildException("token and value are required", location);
    }
    project.addFilter(token, value);
  }
}
