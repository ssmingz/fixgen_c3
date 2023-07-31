class PlaceHold {
  public void drawFocus(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
  }
}
