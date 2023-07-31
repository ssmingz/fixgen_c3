class PlaceHold {
  protected boolean isExcluded(String name) {
    for (int i = 0; i < m_excludes.length; i++) {
      if (matchPath(m_excludes[i], name, m_isCaseSensitive)) {
        return true;
      }
    }
    return false;
  }
}
