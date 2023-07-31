class PlaceHold {
  void drawBackground(int control) {
    if (control == scrolledHandle) {
      if (((style & SWT.NO_FOCUS) == 0) && hooksKeys()) {
        drawFocus(
            control, hasFocus() && drawFocusRing(), hasBorder(), getParentBackground(), inset());
      } else {
        drawBackground(control, getParentBackground());
      }
    } else if ((state & CANVAS) != 0) {
      if ((style & SWT.NO_BACKGROUND) == 0) {
        drawBackground(control, background);
      }
    }
  }
}
