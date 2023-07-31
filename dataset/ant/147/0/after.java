class PlaceHold {
  protected boolean isExcluded(String name) {
    for (int i = 0; i < excludes.length; i++) {
      if (matchPath(excludes[i], name, isCaseSensitive)) {
        return true;
      }
    }
    return false;
  }
}
