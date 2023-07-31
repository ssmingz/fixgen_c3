class PlaceHold {
  public void setExcludes(String excludes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setExcludes(excludes);
  }
}
