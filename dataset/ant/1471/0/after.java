class PlaceHold {
  public synchronized void setIncludes(String includes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludes(includes);
    directoryScanner = null;
  }
}
