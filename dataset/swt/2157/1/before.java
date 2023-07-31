class PlaceHold {
  void setBackgroundPixel(int pixel) {
    if (background == pixel) {
      return;
    }
    background = pixel;
    if (OS.IsWindowEnabled(handle)) {
      _setBackgroundPixel(pixel);
    }
  }
}
