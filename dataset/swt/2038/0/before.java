class PlaceHold {
  public Color getBackground() {
    checkWidget();
    return Color.win32_new(getDisplay(), getBackgroundPixel());
  }
}
