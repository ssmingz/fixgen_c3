class PlaceHold {
  public PatternSet createPatternSet() throws TaskException {
    defaultSetDefined = true;
    return defaultSet.createPatternSet();
  }
}
