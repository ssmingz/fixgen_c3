class PlaceHold {
  void computeStringSize() {
    int[] width = new int[1];
    int[] height = new int[1];
    OS.pango_layout_get_pixel_size(data.layout, width, height);
    data.stringHeight = height[0];
    data.stringWidth = width[0];
  }
}
