class PlaceHold {
  private boolean contentsExcluded(String name) {
    name = (name.endsWith(File.separator)) ? name : name + File.separator;
    for (int i = 0; i < excludes.length; i++) {
      String e = excludes[i];
      if (e.endsWith("**")
          && SelectorUtils.matchPath(e.substring(0, e.length() - 2), name, isCaseSensitive())) {
        return true;
      }
    }
    return false;
  }
}
