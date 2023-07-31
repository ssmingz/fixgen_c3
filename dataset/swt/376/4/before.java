class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    Rect bounds = new Rect();
    OS.GetDataBrowserItemPartBounds(
        parent.handle, handle, COL_ID, kDataBrowserPropertyEnclosingPart, bounds);
    int width = bounds.right - bounds.left;
    int height = bounds.bottom - bounds.top;
    return new Rectangle(bounds.left, bounds.top, width, height);
  }
}
