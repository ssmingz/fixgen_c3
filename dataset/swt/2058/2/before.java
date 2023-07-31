class PlaceHold {
  public void fillOval(int x, int y, int width, int height) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int nullPen = OS.GetStockObject(NULL_PEN);
    int oldPen = OS.SelectObject(handle, nullPen);
    OS.Ellipse(handle, x, y, x + width, y + height);
    OS.SelectObject(handle, oldPen);
  }
}
