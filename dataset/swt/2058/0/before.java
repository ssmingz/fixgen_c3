class PlaceHold {
  public void fillRoundRectangle(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    int nullPen = OS.GetStockObject(NULL_PEN);
    int oldPen = OS.SelectObject(handle, nullPen);
    OS.RoundRect(handle, x, y, x + width, y + height, arcWidth, arcHeight);
    OS.SelectObject(handle, oldPen);
  }
}
