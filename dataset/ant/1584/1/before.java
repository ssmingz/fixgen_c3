class PlaceHold {
  public void setExcludesfile(File excl) throws BuildException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setExcludesfile(excl);
  }
}
