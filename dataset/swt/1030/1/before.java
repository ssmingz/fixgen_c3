class PlaceHold {
  boolean isAvailable() {
    if (parentItem == null) {
      return true;
    }
    if (!parentItem.isExpanded) {
      return false;
    }
    return parentItem.isAvailable();
  }
}
