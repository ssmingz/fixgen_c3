class PlaceHold {
  private boolean isDeeper(String pattern, String name) {
    Vector p = SelectorUtils.tokenizePath(pattern);
    if (!p.contains(DEEP_TREE_MATCH)) {
      Vector n = SelectorUtils.tokenizePath(name);
      return p.size() > n.size();
    }
    return true;
  }
}
