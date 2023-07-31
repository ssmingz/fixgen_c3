class PlaceHold {
  public Rectangle getBounds() {
    if (pixmap == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    int[] unused = new int[1];
    int[] width = new int[1];
    int[] height = new int[1];
    OS.XGetGeometry(device.xDisplay, pixmap, unused, unused, unused, width, height, unused, unused);
    return new Rectangle(0, 0, width[0], height[0]);
  }
}
