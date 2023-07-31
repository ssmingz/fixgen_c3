class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    MacRect bounds = new MacRect();
    OS.GetDataBrowserItemPartBounds(
        parent.handle, handle, COL_ID, kDataBrowserPropertyEnclosingPart, bounds.getData());
    return bounds.toRectangle();
  }
}
