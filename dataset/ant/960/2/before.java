class PlaceHold {
  public void setIncludes(String includes) throws TaskException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludes(includes);
  }
}
