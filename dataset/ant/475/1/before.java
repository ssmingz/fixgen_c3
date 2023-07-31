class PlaceHold {
  public void reset() {
    append = false;
    forceOverwrite = true;
    destinationFile = null;
    encoding = null;
    outputEncoding = null;
    fixLastLine = false;
    filterChains = null;
    footer = null;
    header = null;
    binary = false;
    outputWriter = null;
    textBuffer = null;
    eolString = System.getProperty("line.separator");
    rc = null;
  }
}
