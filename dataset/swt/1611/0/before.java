class PlaceHold {
  void setBackgroundPixel(int pixel) {
    if (background == pixel) {
      return;
    }
    background = pixel;
    if (pixel == (-1)) {
      pixel = defaultBackground();
    }
    OS.SendMessage(handle, PBM_SETBKCOLOR, 0, pixel);
  }
}
