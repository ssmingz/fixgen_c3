class PlaceHold {
  public void drawOval(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int nullBrush = OS.GetStockObject(NULL_BRUSH);
    int oldBrush = OS.SelectObject(handle, nullBrush);
    OS.Ellipse(handle, x, y, x + width, y + height);
    OS.SelectObject(handle, oldBrush);
  }
}
