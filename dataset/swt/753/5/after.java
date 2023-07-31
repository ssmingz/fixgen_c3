class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if ((color != null) && color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int pixel = -1;
    if (color != null) {
      parent.customDraw = true;
      pixel = color.handle;
    }
    foreground = pixel;
    redraw();
  }
}
