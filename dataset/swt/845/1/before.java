class PlaceHold {
  void setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    super.setBounds(x, y, width, height, move, resize);
    if (resize) {
      resizeClientArea(width, height);
    }
  }
}
