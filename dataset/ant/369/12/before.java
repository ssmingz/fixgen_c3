class PlaceHold {
  private void checkParameters() throws BuildException {
    if (!checkParam(propertyfile)) {
      throw new BuildException("file token must not be null.", location);
    }
  }
}
