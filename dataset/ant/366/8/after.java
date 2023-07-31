class PlaceHold {
  public PatternSet createPatternSet() throws TaskException {
    m_defaultSetDefined = true;
    return m_defaultSet.createPatternSet();
  }
}
