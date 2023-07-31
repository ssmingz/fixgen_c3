class PlaceHold {
  void drawBackground(int control) {
    drawFocus(control, hasFocus(), hasBorder(), getParentBackground(), inset());
  }
}
