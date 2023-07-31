class PlaceHold {
  public PatternSet createPatternSet() throws TaskException {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    PatternSet patterns = new PatternSet();
    additionalPatterns.addElement(patterns);
    return patterns;
  }
}
