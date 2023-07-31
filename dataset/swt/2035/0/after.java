class PlaceHold {
  public Rectangle getClientArea() {
    checkDevice();
    MacRect bounds = new MacRect();
    int gdh = OS.GetMainDevice();
    OS.GetAvailableWindowPositioningBounds(gdh, bounds.getData());
    return bounds.toRectangle();
  }
}
