class PlaceHold {
  public void drawPoint(int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    OS.SetPixel(handle, x, y, OS.GetTextColor(handle));
  }
}
