class PlaceHold {
  boolean outlineView_shouldCollapseItem(int outlineView, int ref) {
    if (!ignoreExpand) {
      TreeItem item = ((TreeItem) (OS.JNIGetObject(OS.objc_msgSend(ref, sel_tag))));
      Event event = new Event();
      event.item = item;
      sendEvent(Collapse, event);
      item.expanded = false;
    }
    return true;
  }
}
