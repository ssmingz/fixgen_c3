class PlaceHold {
  public void setForeground(Color color) {
    checkWidget();
    if (color != null) {
      if (color.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
    }
    double[] foreground = (color != null) ? color.handle : null;
    if (equals(foreground, this.foreground)) {
      return;
    }
    this.foreground = foreground;
    setForeground(foreground);
    redrawWidget(view, false);
  }
}
