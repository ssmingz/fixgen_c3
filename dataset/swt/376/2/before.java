class PlaceHold {
  public Rectangle getBounds() {
    checkDevice();
    Rect bounds = new Rect();
    if (fGDeviceHandle != 0) {
      int pm = OS.getgdPMap(fGDeviceHandle);
      if (pm != 0) {
        OS.GetPixBounds(pm, bounds);
      }
    }
    int width = bounds.right - bounds.left;
    int height = bounds.bottom - bounds.top;
    return new Rectangle(bounds.left, bounds.top, width, height);
  }
}
