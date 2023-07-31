class PlaceHold {
  void onMouseUp(Event event) {
    int x = event.x;
    int y = event.y;
    if (focusIndex == (-1)) {
      return;
    }
    ExpandItem item = items[focusIndex];
    boolean hover =
        (((item.x <= x) && (x < (item.x + item.width))) && (item.y <= y))
            && (y < (item.y + ExpandBar.HEADER_HEIGHT));
    if (hover) {
      Event ev = new Event();
      ev.item = item;
      notifyListeners(item.expanded ? SWT.Collapse : SWT.Expand, ev);
      item.expanded = !item.expanded;
      showItem(focusIndex);
    }
  }
}
