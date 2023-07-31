class PlaceHold {
  void drawBackground(int control, int context) {
    if ((style & SWT.SEPARATOR) != 0) {
      fillBackground(control, context, null);
    }
  }
}
