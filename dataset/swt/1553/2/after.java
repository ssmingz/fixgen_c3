class PlaceHold {
  void setForegroundPixel(int pixel) {
    if (pixel == (-1)) {
      pixel = defaultForeground();
    }
    OS.SendMessage(handle, RB_SETTEXTCOLOR, 0, pixel);
    setItemColors(pixel, ((int) (OS.SendMessage(handle, RB_GETBKCOLOR, 0, 0))));
  }
}
