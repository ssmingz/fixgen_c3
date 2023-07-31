class PlaceHold {
  boolean outlineView_shouldExpandItem(int outlineView, int ref) {
    final TreeItem item = ((TreeItem) (display.getWidget(ref)));
    if (!ignoreExpand) {
      Event event = new Event();
      event.item = item;
      sendEvent(Expand, event);
      item.expanded = true;
      ignoreExpand = true;
      ((NSOutlineView) (view)).expandItem(item.handle);
      ignoreExpand = false;
      return false;
    }
    return item.expanded;
  }
}
