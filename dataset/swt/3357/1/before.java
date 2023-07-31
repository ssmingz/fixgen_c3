class PlaceHold {
  public void fillArc(int x, int y, int width, int height, int startAngle, int endAngle) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int x1;
    int y1;
    int x2;
    int y2;
    int tmp;
    boolean isNegative;
    if (width < 0) {
      x = x + width;
      width = -width;
    }
    if (height < 0) {
      y = y + height;
      height = -height;
    }
    if (((width == 0) || (height == 0)) || (endAngle == 0)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((endAngle >= 360) || (endAngle <= (-360))) {
      x1 = x2 = x + width;
      y1 = y2 = y + (height / 2);
    } else {
      isNegative = endAngle < 0;
      endAngle = endAngle + startAngle;
      if (isNegative) {
        tmp = startAngle;
        startAngle = endAngle;
        endAngle = tmp;
      }
      x1 = (Compatibility.cos(startAngle, width) + x) + (width / 2);
      y1 = (((-1) * Compatibility.sin(startAngle, height)) + y) + (height / 2);
      x2 = (Compatibility.cos(endAngle, width) + x) + (width / 2);
      y2 = (((-1) * Compatibility.sin(endAngle, height)) + y) + (height / 2);
    }
    int nullPen = OS.GetStockObject(NULL_PEN);
    int oldPen = OS.SelectObject(handle, nullPen);
    OS.Pie(handle, x, y, (x + width) + 1, (y + height) + 1, x1, y1, x2, y2);
    OS.SelectObject(handle, oldPen);
  }
}
