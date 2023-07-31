class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    if (!parent.checkData(this, true)) {
      error(ERROR_WIDGET_DISPOSED);
    }
    Rect rect = new Rect();
    int columnId = (parent.columnCount == 0) ? parent.column_id : parent.columns[0].id;
    if (OS.GetDataBrowserItemPartBounds(
            parent.handle, id, columnId, kDataBrowserPropertyContentPart, rect)
        != OS.noErr) {
      return new Rectangle(0, 0, 0, 0);
    }
    int x = rect.left;
    int y = rect.top;
    int width = 0;
    if (image != null) {
      Rectangle bounds = image.getBounds();
      x += bounds.width + 2;
    }
    GC gc = new GC(parent);
    Point extent = gc.stringExtent(text);
    gc.dispose();
    width += extent.x;
    if (parent.columnCount > 0) {
      width = Math.min(width, rect.right - x);
    }
    int height = rect.bottom - rect.top;
    return new Rectangle(x, y, width, height);
  }
}
