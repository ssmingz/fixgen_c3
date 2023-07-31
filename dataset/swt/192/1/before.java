class PlaceHold {
  public void setBackground(Color color) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (color == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (OS.GetBkColor(handle) == color.handle) {
      return;
    }
    OS.SetBkColor(handle, color.handle);
    int newBrush = OS.CreateSolidBrush(color.handle);
    int oldBrush = OS.SelectObject(handle, newBrush);
    OS.DeleteObject(oldBrush);
  }
}
