class PlaceHold {
  boolean outlineView_shouldCollapseItem(int id, int sel, int outlineView, int itemID) {
    TreeItem item = ((TreeItem) (display.getWidget(itemID)));
    if (!ignoreExpand) {
      Event event = new Event();
      event.item = item;
      sendEvent(Collapse, event);
      if (isDisposed()) {
        return false;
      }
      item.expanded = false;
      ignoreExpand = true;
      ((NSOutlineView) (view)).collapseItem(item.handle);
      ignoreExpand = false;
      setScrollWidth();
      return false;
    }
    return !item.expanded;
  }
}
