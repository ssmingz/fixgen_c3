class PlaceHold {
  void drawBackground(int control) {
    drawFocus(control, hasFocus() && drawFocusRing(), hasBorder(), getParentBackground(), inset());
  }
}
