class PlaceHold {
  void setBackgroundPixel(int pixel) {
    if (findImageControl() != null) {
      return;
    }
    if (OS.IsWindowEnabled(handle)) {
      _setBackgroundPixel(pixel);
    }
  }
}
