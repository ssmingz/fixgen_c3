class PlaceHold {
  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(DRAW);
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    if (((width == 0) || (height == 0)) || (arcAngle == 0)) {
      return;
    }
    int gdipGraphics = data.gdipGraphics;
    if (gdipGraphics != 0) {
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeNone);
      }
      if (width == height) {
        Gdip.Graphics_DrawArc(
            gdipGraphics, data.gdipPen, x, y, width, height, -startAngle, -arcAngle);
      } else {
        int path = Gdip.GraphicsPath_new(FillModeAlternate);
        if (path == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        int matrix = Gdip.Matrix_new(width, 0, 0, height, x, y);
        if (matrix == 0) {
          SWT.error(ERROR_NO_HANDLES);
        }
        Gdip.GraphicsPath_AddArc(path, 0, 0, 1, 1, -startAngle, -arcAngle);
        Gdip.GraphicsPath_Transform(path, matrix);
        Gdip.Graphics_DrawPath(gdipGraphics, data.gdipPen, path);
        Gdip.Matrix_delete(matrix);
        Gdip.GraphicsPath_delete(path);
      }
      if ((data.lineWidth == 0) || ((data.lineWidth % 2) == 1)) {
        Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, PixelOffsetModeHalf);
      }
      return;
    }
    if (OS.IsWinCE) {
      if (arcAngle < 0) {
        startAngle += arcAngle;
        arcAngle = -arcAngle;
      }
      if (arcAngle > 360) {
        arcAngle = 360;
      }
      int[] points = new int[(arcAngle + 1) * 2];
      int cteX = (2 * x) + width;
      int cteY = (2 * y) + height;
      int index = 0;
      for (int i = 0; i <= arcAngle; i++) {
        points[index++] = (Compatibility.cos(startAngle + i, width) + cteX) >> 1;
        points[index++] = (cteY - Compatibility.sin(startAngle + i, height)) >> 1;
      }
      OS.Polyline(handle, points, points.length / 2);
    } else {
      int x1;
      int y1;
      int x2;
      int y2;
      int tmp;
      boolean isNegative;
      if ((arcAngle >= 360) || (arcAngle <= (-360))) {
        x1 = x2 = x + width;
        y1 = y2 = y + (height / 2);
      } else {
        isNegative = arcAngle < 0;
        arcAngle = arcAngle + startAngle;
        if (isNegative) {
          tmp = startAngle;
          startAngle = arcAngle;
          arcAngle = tmp;
        }
        x1 = (Compatibility.cos(startAngle, width) + x) + (width / 2);
        y1 = (((-1) * Compatibility.sin(startAngle, height)) + y) + (height / 2);
        x2 = (Compatibility.cos(arcAngle, width) + x) + (width / 2);
        y2 = (((-1) * Compatibility.sin(arcAngle, height)) + y) + (height / 2);
      }
      OS.Arc(handle, x, y, (x + width) + 1, (y + height) + 1, x1, y1, x2, y2);
    }
  }
}
