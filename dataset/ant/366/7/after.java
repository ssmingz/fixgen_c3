class PlaceHold {
  public void setDefaultexcludes(boolean useDefaultExcludes) throws TaskException {
    m_defaultSetDefined = true;
    m_defaultSet.setDefaultexcludes(useDefaultExcludes);
  }
}
