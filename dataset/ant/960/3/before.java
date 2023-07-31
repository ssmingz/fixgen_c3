class PlaceHold {
  public void setIncludesfile(File incl) throws TaskException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludesfile(incl);
  }
}
