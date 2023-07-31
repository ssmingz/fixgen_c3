class PlaceHold {
  void setForegroundPixel(int pixel) {
    if (foreground == pixel) {
      return;
    }
    foreground = pixel;
    if (pixel == (-1)) {
      pixel = OS.CLR_DEFAULT;
    }
    OS.SendMessage(handle, PBM_SETBARCOLOR, 0, pixel);
  }
}
