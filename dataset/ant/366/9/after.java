class PlaceHold {
  public NameEntry createInclude() throws TaskException {
    m_defaultSetDefined = true;
    return m_defaultSet.createInclude();
  }
}
