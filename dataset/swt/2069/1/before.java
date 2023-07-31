class PlaceHold {
  public Color getBackground() {
    checkWidget();
    return Color.motif_new(display, getXColor(getBackgroundPixel()));
  }
}
