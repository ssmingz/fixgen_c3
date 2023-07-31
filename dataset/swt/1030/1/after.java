class PlaceHold {
  boolean isAvailable() {
    if (parentItem == null) {
      return true;
    }
    if (!parentItem.expanded) {
      return false;
    }
    return parentItem.isAvailable();
  }
}
