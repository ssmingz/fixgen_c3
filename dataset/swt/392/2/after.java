class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    int result = super.setBounds(x, y, width, height, move, resize);
    if (((result & RESIZED) != 0) && (layout != null)) {
      layout.layout(this, false);
    }
    return result;
  }
}
