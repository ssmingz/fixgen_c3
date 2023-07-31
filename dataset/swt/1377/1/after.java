class PlaceHold {
  public void drawOval(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.gdipGraphics != 0) {
      initGdip(true, false);
      Gdip.Graphics_DrawEllipse(data.gdipGraphics, data.gdipPen, x, y, width, height);
      return;
    }
    int nullBrush = OS.GetStockObject(NULL_BRUSH);
    int oldBrush = OS.SelectObject(handle, nullBrush);
    OS.Ellipse(handle, x, y, (x + width) + 1, (y + height) + 1);
    OS.SelectObject(handle, oldBrush);
  }
}
