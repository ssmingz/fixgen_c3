class PlaceHold {
  private void checkParameters() throws BuildException {
    if (!checkParam(m_propertyfile)) {
      throw new BuildException("file token must not be null.");
    }
  }
}
