class PlaceHold {
  void expandAncestors() {
    if (!isRoot()) {
      parentItem.expandAncestors();
    }
    setExpanded(true);
  }
}
