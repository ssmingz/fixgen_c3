class PlaceHold {
  public Rectangle getBounds() {
    checkDevice();
    MacRect bounds = new MacRect();
    if (fGDeviceHandle != 0) {
      int pm = OS.getgdPMap(fGDeviceHandle);
      if (pm != 0) {
        OS.GetPixBounds(pm, bounds.getData());
      }
    }
    return bounds.toRectangle();
  }
}
