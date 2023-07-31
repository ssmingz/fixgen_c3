class PlaceHold {
  void expandItem_expandChildren(int id, int sel, int itemID, boolean children) {
    TreeItem item = ((TreeItem) (display.getWidget(itemID)));
    if (!ignoreExpand) {
      item.sendExpand(true, children);
    }
    ignoreExpand = true;
    super.expandItem_expandChildren(id, sel, itemID, children);
    ignoreExpand = false;
    if (isDisposed() || item.isDisposed()) {
      return;
    }
    if (!children) {
      ignoreExpand = true;
      TreeItem[] items = item.items;
      for (int i = 0; i < item.itemCount; i++) {
        if (items[i] != null) {
          items[i].updateExpanded();
        }
      }
      ignoreExpand = false;
    }
    setScrollWidth(false, item.items, true);
  }
}
