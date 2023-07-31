class PlaceHold {
  public void drawPolygon(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int gdipGraphics = data.gdipGraphics;
    if (gdipGraphics != 0) {
      initGdip(true, false);
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeNone);
      }
      Gdip.Graphics_DrawPolygon(gdipGraphics, data.gdipPen, pointArray, pointArray.length / 2);
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeHalf);
      }
      return;
    }
    int nullBrush = OS.GetStockObject(NULL_BRUSH);
    int oldBrush = OS.SelectObject(handle, nullBrush);
    OS.Polygon(handle, pointArray, pointArray.length / 2);
    OS.SelectObject(handle, oldBrush);
  }
}
