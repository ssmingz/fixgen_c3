class PlaceHold {
  boolean outlineView_shouldCollapseItem(int outlineView, int ref) {
    TreeItem item = ((TreeItem) (display.getWidget(ref)));
    if (!ignoreExpand) {
      Event event = new Event();
      event.item = item;
      sendEvent(Collapse, event);
      item.expanded = false;
      ignoreExpand = true;
      ((NSOutlineView) (view)).collapseItem_(item.handle);
      ignoreExpand = false;
      return false;
    }
    return !item.expanded;
  }
}
