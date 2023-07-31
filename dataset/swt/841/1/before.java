class PlaceHold {
  public void setForeground(int index, Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int count = Math.max(1, parent.getColumnCount());
    if ((0 > index) || (index > (count - 1))) {
      return;
    }
    int pixel = -1;
    if (color != null) {
      parent.customDraw = true;
      pixel = color.handle;
    }
    if (cellForeground == null) {
      cellForeground = new int[count];
      for (int i = 0; i < count; i++) {
        cellForeground[i] = -1;
      }
    }
    if (cellForeground[index] == pixel) {
      return;
    }
    cellForeground[index] = pixel;
    redraw(index, false, true);
  }
}
