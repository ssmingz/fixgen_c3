class PlaceHold {
  void setForegroundPixel(int pixel) {
    if (foreground == pixel) {
      return;
    }
    foreground = pixel;
    if (pixel == (-1)) {
      pixel = defaultForeground();
    }
    OS.SendMessage(handle, PBM_SETBARCOLOR, 0, pixel);
  }
}
