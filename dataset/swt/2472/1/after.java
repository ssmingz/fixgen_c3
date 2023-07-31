class PlaceHold {
  void expandAncestors() {
    if (parentItem != null) {
      parentItem.expandAncestors();
    }
    setExpanded(true);
  }
}
