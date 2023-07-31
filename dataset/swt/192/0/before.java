class PlaceHold {
  public void setForeground(Color color) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (color == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (OS.GetTextColor(handle) == color.handle) {
      return;
    }
    int hPen = OS.GetCurrentObject(handle, OBJ_PEN);
    LOGPEN logPen = new LOGPEN();
    OS.GetObject(hPen, sizeof, logPen);
    OS.SetTextColor(handle, color.handle);
    int newPen = OS.CreatePen(logPen.lopnStyle, logPen.x, color.handle);
    int oldPen = OS.SelectObject(handle, newPen);
    OS.DeleteObject(oldPen);
  }
}
