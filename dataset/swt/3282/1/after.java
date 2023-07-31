class PlaceHold {
  public Rectangle getClipping() {
    if (data.clipRgn == 0) {
      int[] width = new int[1];
      int[] height = new int[1];
      OS.gdk_drawable_get_size(data.drawable, width, height);
      return new Rectangle(0, 0, width[0], height[0]);
    }
    GdkRectangle rect = new GdkRectangle();
    OS.gdk_region_get_clipbox(data.clipRgn, rect);
    return new Rectangle(rect.x, rect.y, rect.width, rect.height);
  }
}
