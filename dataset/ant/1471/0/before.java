class PlaceHold {
  public void setIncludes(String includes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludes(includes);
  }
}
