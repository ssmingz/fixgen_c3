class PlaceHold {
  public synchronized void setExcludes(String excludes) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setExcludes(excludes);
    directoryScanner = null;
  }
}
