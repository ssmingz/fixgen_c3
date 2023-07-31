class PlaceHold {
  void drawBackground(int control, int context) {
    if ((style & SWT.SEPARATOR) != 0) {
      drawBackground(control, context, background);
    }
  }
}
