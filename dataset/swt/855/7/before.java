class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    Color oldColor = background;
    if (oldColor == color) {
      return;
    }
    background = color;
    if ((oldColor != null) && oldColor.equals(color)) {
      return;
    }
    cached = true;
    ((NSOutlineView) (parent.view)).reloadItem_(handle);
  }
}
