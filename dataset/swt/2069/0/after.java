class PlaceHold {
  public Color getBackground() {
    checkWidget();
    Control control = findBackgroundControl();
    if (control == null) {
      control = this;
    }
    return Color.win32_new(display, control.getBackgroundPixel());
  }
}
