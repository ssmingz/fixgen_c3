class PlaceHold {
  public synchronized PatternSet createPatternSet() {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    PatternSet patterns = new PatternSet();
    additionalPatterns.add(patterns);
    directoryScanner = null;
    return patterns;
  }
}
