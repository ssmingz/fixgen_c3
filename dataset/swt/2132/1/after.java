class PlaceHold {
  public void drawRectangle(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.gdipGraphics != 0) {
      initGdip(true, false);
      Gdip.Graphics_DrawRectangle(data.gdipGraphics, data.gdipPen, x, y, width, height);
      return;
    }
    int hOld = OS.SelectObject(handle, OS.GetStockObject(NULL_BRUSH));
    OS.Rectangle(handle, x, y, (x + width) + 1, (y + height) + 1);
    OS.SelectObject(handle, hOld);
  }
}
