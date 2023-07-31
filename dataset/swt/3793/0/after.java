class PlaceHold {
  public Rectangle getBounds() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int[] unused = new int[1];
    int[] width = new int[1];
    int[] height = new int[1];
    OS.XGetGeometry(device.xDisplay, pixmap, unused, unused, unused, width, height, unused, unused);
    return new Rectangle(0, 0, width[0], height[0]);
  }
}
