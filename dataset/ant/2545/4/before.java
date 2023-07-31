class PlaceHold {
  protected boolean isIncluded(final String name) {
    for (int i = 0; i < m_includes.length; i++) {
      if (matchPath(m_includes[i], name, m_isCaseSensitive)) {
        return true;
      }
    }
    return false;
  }
}
