class PlaceHold {
  protected boolean couldHoldIncluded(final String name) {
    for (int i = 0; i < m_includes.length; i++) {
      if (matchPatternStart(m_includes[i], name, m_isCaseSensitive)) {
        return true;
      }
    }
    return false;
  }
}
