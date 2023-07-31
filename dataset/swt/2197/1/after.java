class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int pixel = -1;
    if (color != null) {
      parent.customDraw = true;
      pixel = color.handle;
    }
    background = pixel;
    redraw();
  }
}
