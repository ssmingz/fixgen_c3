class PlaceHold {
  public void fatalError(TransformerException e) {
    logError(e, "Fatal Error");
    throw new BuildException("Fatal error during transformation", e);
  }
}
