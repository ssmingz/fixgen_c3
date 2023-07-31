class PlaceHold {
  public Color getBackground() {
    checkWidget();
    Control control = findBackgroundControl();
    if (control == null) {
      control = this;
    }
    return Color.motif_new(display, getXColor(control.getBackgroundPixel()));
  }
}
