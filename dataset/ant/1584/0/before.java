class PlaceHold {
  public void setIncludesfile(File incl) throws BuildException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludesfile(incl);
  }
}
