class PlaceHold {
  public CTabItem getItem(Point pt) {
    if (items.length == 0) {
      return null;
    }
    Point size = getSize();
    Rectangle trim = renderer.computeTrim(PART_HEADER, NONE, 0, 0, 0, 0);
    if (size.x <= trim.width) {
      return null;
    }
    if (showChevron && chevronRect.contains(pt)) {
      return null;
    }
    for (int i = 0; i < priority.length; i++) {
      CTabItem item = items[priority[i]];
      Rectangle rect = item.getBounds();
      if (rect.contains(pt)) {
        return item;
      }
    }
    return null;
  }
}
