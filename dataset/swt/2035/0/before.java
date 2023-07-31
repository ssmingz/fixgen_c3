class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    Rect bounds = new Rect();
    int gdh = OS.GetMainDevice();
    OS.GetAvailableWindowPositioningBounds(gdh, bounds);
    int width = bounds.right - bounds.left;
    int height = bounds.bottom - bounds.top;
    return new Rectangle(bounds.left, bounds.top, width, height);
  }
}
