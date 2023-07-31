class PlaceHold {
  void updateExpanded() {
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
