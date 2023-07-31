class PlaceHold {
  void collapseItem_collapseChildren(int id, int sel, int itemID, boolean children) {
    TreeItem item = ((TreeItem) (display.getWidget(itemID)));
    if (!ignoreExpand) {
      item.sendExpand(false, children);
    }
    ignoreExpand = true;
    super.collapseItem_collapseChildren(id, sel, itemID, children);
    ignoreExpand = false;
    if (isDisposed() || item.isDisposed()) {
      return;
    }
    setScrollWidth();
  }
}
