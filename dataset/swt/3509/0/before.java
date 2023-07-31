class PlaceHold {
  public Color getForeground() {
    checkWidget();
    return Color.carbon_new(getDisplay(), getForegroundPixel(), false);
  }
}
