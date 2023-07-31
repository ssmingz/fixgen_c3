class PlaceHold {
  public Rectangle getBounds() {
    int[] width = new int[1];
    int[] height = new int[1];
    OS.gdk_drawable_get_size(pixmap, width, height);
    return new Rectangle(0, 0, width[0], height[0]);
  }
}
