class PlaceHold {
  boolean outlineView_shouldExpandItem(int id, int sel, int outlineView, int itemID) {
    final TreeItem item = ((TreeItem) (display.getWidget(itemID)));
    if (!ignoreExpand) {
      Event event = new Event();
      event.item = item;
      sendEvent(Expand, event);
      if (isDisposed()) {
        return false;
      }
      item.expanded = true;
      ignoreExpand = true;
      ((NSOutlineView) (view)).expandItem(item.handle);
      ignoreExpand = false;
      return false;
    }
    return item.expanded;
  }
}
