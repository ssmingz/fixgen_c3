class PlaceHold {
  public synchronized void setIncludesfile(File incl) throws BuildException {
    if (isReference()) {
      throw tooManyAttributes();
    }
    defaultPatterns.setIncludesfile(incl);
    directoryScanner = null;
  }
}
