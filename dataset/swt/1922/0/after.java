class PlaceHold {
  public void drawLine(int x1, int y1, int x2, int y2) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(DRAW);
    int gdipGraphics = data.gdipGraphics;
    if (gdipGraphics != 0) {
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeNone);
      }
      Gdip.Graphics_DrawLine(gdipGraphics, data.gdipPen, x1, y1, x2, y2);
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeHalf);
      }
      return;
    }
    if (OS.IsWinCE) {
      int[] points = new int[] {x1, y1, x2, y2};
      OS.Polyline(handle, points, points.length / 2);
    } else {
      OS.MoveToEx(handle, x1, y1, 0);
      OS.LineTo(handle, x2, y2);
    }
    if (data.lineWidth <= 1) {
      OS.SetPixel(handle, x2, y2, data.foreground);
    }
  }
}
