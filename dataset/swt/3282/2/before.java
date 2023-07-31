class PlaceHold {
  public void drawImage(Image image, int x, int y) {
    if (image == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int pixmap = image.pixmap;
    int[] unused = new int[1];
    int[] width = new int[1];
    int[] height = new int[1];
    OS.gdk_window_get_geometry(pixmap, unused, unused, width, height, unused);
    drawImage(image, 0, 0, width[0], height[0], x, y, width[0], height[0]);
  }
}
