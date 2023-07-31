class PlaceHold {
  public void fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(FILL);
    if (data.gdipGraphics != 0) {
      fillRoundRectangleGdip(
          data.gdipGraphics, data.gdipBrush, x, y, width, height, arcWidth, arcHeight);
      return;
    }
    OS.RoundRect(handle, x, y, (x + width) + 1, (y + height) + 1, arcWidth, arcHeight);
  }
}
