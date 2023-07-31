class PlaceHold {
  public void drawRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (data.gdipGraphics != 0) {
      initGdip(true, false);
      drawRoundRectangleGdip(
          data.gdipGraphics, data.gdipPen, x, y, width, height, arcWidth, arcHeight);
      return;
    }
    if (OS.IsWinCE) {
      if ((width == 0) || (height == 0)) {
        return;
      }
      if ((arcWidth == 0) || (arcHeight == 0)) {
        drawRectangle(x, y, width, height);
        return;
      }
      if (width < 0) {
        x += width;
        width = -width;
      }
      if (height < 0) {
        y += height;
        height = -height;
      }
      if (arcWidth < 0) {
        arcWidth = -arcWidth;
      }
      if (arcHeight < 0) {
        arcHeight = -arcHeight;
      }
      if (arcWidth > width) {
        arcWidth = width;
      }
      if (arcHeight > height) {
        arcHeight = height;
      }
      if (arcWidth < width) {
        drawLine(x + (arcWidth / 2), y, (x + width) - (arcWidth / 2), y);
        drawLine(x + (arcWidth / 2), y + height, (x + width) - (arcWidth / 2), y + height);
      }
      if (arcHeight < height) {
        drawLine(x, y + (arcHeight / 2), x, (y + height) - (arcHeight / 2));
        drawLine(x + width, y + (arcHeight / 2), x + width, (y + height) - (arcHeight / 2));
      }
      if ((arcWidth != 0) && (arcHeight != 0)) {
        drawArc(x, y, arcWidth, arcHeight, 90, 90);
        drawArc((x + width) - arcWidth, y, arcWidth, arcHeight, 0, 90);
        drawArc((x + width) - arcWidth, (y + height) - arcHeight, arcWidth, arcHeight, 0, -90);
        drawArc(x, (y + height) - arcHeight, arcWidth, arcHeight, 180, 90);
      }
    } else {
      int nullBrush = OS.GetStockObject(NULL_BRUSH);
      int oldBrush = OS.SelectObject(handle, nullBrush);
      OS.RoundRect(handle, x, y, (x + width) + 1, (y + height) + 1, arcWidth, arcHeight);
      OS.SelectObject(handle, oldBrush);
    }
  }
}
