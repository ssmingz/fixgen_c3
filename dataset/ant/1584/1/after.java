class PlaceHold {
  public synchronized void setExcludesfile(File excl) throws BuildException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setExcludesfile(excl);
    directoryScanner = null;
  }
}
