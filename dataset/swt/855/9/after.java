class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    Color oldColor = foreground;
    if (oldColor == color) {
      return;
    }
    foreground = color;
    if ((oldColor != null) && oldColor.equals(color)) {
      return;
    }
    cached = true;
    ((NSOutlineView) (parent.view)).reloadItem(handle);
  }
}
