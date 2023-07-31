class PlaceHold {
  void computeStringSize() {
    int[] width = new int[1];
    int[] height = new int[1];
    OS.pango_layout_get_size(data.layout, width, height);
    data.stringHeight = OS.PANGO_PIXELS(height[0]);
    data.stringWidth = OS.PANGO_PIXELS(width[0]);
  }
}
