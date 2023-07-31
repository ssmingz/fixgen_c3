class PlaceHold {
  public void drawPoint(int x, int y) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.gdipGraphics != 0) {
      initGdip(false, true);
      Gdip.Graphics_FillRectangle(data.gdipGraphics, data.gdipBrush, x, y, 1, 1);
      return;
    }
    OS.SetPixel(handle, x, y, OS.GetTextColor(handle));
  }
}
