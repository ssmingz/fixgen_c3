class PlaceHold {
  protected boolean isIncluded(String name) {
    for (int i = 0; i < includes.length; i++) {
      if (matchPath(includes[i], name, isCaseSensitive)) {
        return true;
      }
    }
    return false;
  }
}
