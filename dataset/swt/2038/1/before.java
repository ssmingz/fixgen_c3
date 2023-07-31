class PlaceHold {
  public Color getForeground() {
    checkWidget();
    return Color.win32_new(getDisplay(), getForegroundPixel());
  }
}
