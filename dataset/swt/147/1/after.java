class PlaceHold {
  void updateExpanded() {
    if (itemCount == 0) {
      return;
    }
    NSOutlineView outlineView = ((NSOutlineView) (parent.view));
    if (expanded != outlineView.isItemExpanded(handle)) {
      if (expanded) {
        outlineView.expandItem(handle);
      } else {
        outlineView.collapseItem(handle);
      }
    }
    for (int i = 0; i < itemCount; i++) {
      if (items[i] != null) {
        items[i].updateExpanded();
      }
    }
  }
}
