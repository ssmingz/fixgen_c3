class PlaceHold {
  void redraw(boolean all) {
    long parentHandle = parent.handle;
    int headerHeight = parent.getBandHeight();
    RECT rect = new RECT();
    int left = (all) ? x : (x + width) - headerHeight;
    OS.SetRect(rect, left, y, x + width, y + headerHeight);
    OS.InvalidateRect(parentHandle, rect, true);
    if (imageHeight > headerHeight) {
      OS.SetRect(
          rect,
          x + ExpandItem.TEXT_INSET,
          (y + headerHeight) - imageHeight,
          (x + ExpandItem.TEXT_INSET) + imageWidth,
          y);
      OS.InvalidateRect(parentHandle, rect, true);
    }
    if (!parent.isAppThemed()) {
      OS.SetRect(rect, x, y + headerHeight, x + width, ((y + headerHeight) + height) + 1);
      OS.InvalidateRect(parentHandle, rect, true);
    }
  }
}
