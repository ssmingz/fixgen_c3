class PlaceHold {
  public void setForeground(int index, Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.columnCount);
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    if (cellForeground == null) {
      if (color == null) {
        return;
      }
      cellForeground = new Color[count];
    }
    Color oldColor = cellForeground[index];
    if (oldColor == color) {
      return;
    }
    cellForeground[index] = color;
    if ((oldColor != null) && oldColor.equals(color)) {
      return;
    }
    cached = true;
    ((NSOutlineView) (parent.view)).reloadItem(handle);
  }
}
