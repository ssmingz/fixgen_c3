class PlaceHold {
  public void drawPolygon(int[] pointArray) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (pointArray == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    checkGC(DRAW);
    int gdipGraphics = data.gdipGraphics;
    if (gdipGraphics != 0) {
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeNone);
      }
      Gdip.Graphics_DrawPolygon(gdipGraphics, data.gdipPen, pointArray, pointArray.length / 2);
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeHalf);
      }
      return;
    }
    OS.Polygon(handle, pointArray, pointArray.length / 2);
  }
}
