class PlaceHold {
  public void setBackground(int index, Color color) {
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
    if (cellBackground == null) {
      cellBackground = new int[count];
      for (int i = 0; i < count; i++) {
        cellBackground[i] = -1;
      }
    }
    if (cellBackground[index] == pixel) {
      return;
    }
    cellBackground[index] = pixel;
    if ((parent.style & SWT.VIRTUAL) != 0) {
      cached = true;
    }
    redraw(index, true, true);
  }
}
