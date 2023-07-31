class PlaceHold {
  public void drawPolyline(int[] pointArray) {
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
      Gdip.Graphics_DrawLines(gdipGraphics, data.gdipPen, pointArray, pointArray.length / 2);
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeHalf);
      }
      return;
    }
    OS.Polyline(handle, pointArray, pointArray.length / 2);
    int length = pointArray.length;
    if (length >= 2) {
      if (data.lineWidth <= 1) {
        OS.SetPixel(handle, pointArray[length - 2], pointArray[length - 1], data.foreground);
      }
    }
  }
}
