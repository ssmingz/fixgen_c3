class PlaceHold {
  public Rectangle getBounds() {
    int[] unused = new int[1];
    int[] width = new int[1];
    int[] height = new int[1];
    OS.gdk_window_get_geometry(pixmap, unused, unused, width, height, unused);
    return new Rectangle(0, 0, width[0], height[0]);
  }
}
