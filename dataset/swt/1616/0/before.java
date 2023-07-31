class PlaceHold {
  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    checkGC(FILL);
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
      if (width == height) {
        Gdip.Graphics_FillPie(
            gdipGraphics, data.gdipBrush, x, y, width, height, -startAngle, -arcAngle);
      } else {
        int state = Gdip.Graphics_Save(gdipGraphics);
        Gdip.Graphics_TranslateTransform(gdipGraphics, x, y, MatrixOrderPrepend);
        Gdip.Graphics_ScaleTransform(gdipGraphics, width, height, MatrixOrderPrepend);
        Gdip.Graphics_FillPie(gdipGraphics, data.gdipBrush, 0, 0, 1, 1, -startAngle, -arcAngle);
        Gdip.Graphics_Restore(gdipGraphics, state);
      }
      return;
    }
    if (OS.IsWinCE) {
      if (arcAngle < 0) {
        startAngle += arcAngle;
        arcAngle = -arcAngle;
      }
      boolean drawSegments = true;
      if (arcAngle >= 360) {
        arcAngle = 360;
        drawSegments = false;
      }
      int[] points = new int[((arcAngle + 1) * 2) + (drawSegments ? 4 : 0)];
      int cteX = (2 * x) + width;
      int cteY = (2 * y) + height;
      int index = (drawSegments) ? 2 : 0;
      for (int i = 0; i <= arcAngle; i++) {
        points[index++] = (Compatibility.cos(startAngle + i, width) + cteX) >> 1;
        points[index++] = (cteY - Compatibility.sin(startAngle + i, height)) >> 1;
      }
      if (drawSegments) {
        points[0] = points[points.length - 2] = cteX >> 1;
        points[1] = points[points.length - 1] = cteY >> 1;
      }
      OS.Polygon(handle, points, points.length / 2);
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
      OS.Pie(handle, x, y, (x + width) + 1, (y + height) + 1, x1, y1, x2, y2);
    }
  }
}
