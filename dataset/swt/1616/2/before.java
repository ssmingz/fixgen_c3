class PlaceHold {
  public void fillOval(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(FILL);
    if (data.gdipGraphics != 0) {
      Gdip.Graphics_FillEllipse(data.gdipGraphics, data.gdipBrush, x, y, width, height);
      return;
    }
    OS.Ellipse(handle, x, y, (x + width) + 1, (y + height) + 1);
  }
}
