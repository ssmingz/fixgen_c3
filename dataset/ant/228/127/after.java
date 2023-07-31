class PlaceHold {
  public PatternSet createPatternSet() throws TaskException {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    PatternSet patterns = new PatternSet();
    additionalPatterns.add(patterns);
    return patterns;
  }
}
