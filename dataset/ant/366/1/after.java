class PlaceHold {
  public void setExcludes(String excludes) throws TaskException {
    m_defaultSetDefined = true;
    m_defaultSet.setExcludes(excludes);
  }
}
