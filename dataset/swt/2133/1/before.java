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
    data.foreground = color.handle;
    OS.SetTextColor(handle, color.handle);
    setPen(color.handle, -1, -1, -1, -1);
  }
}
