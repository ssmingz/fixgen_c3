class PlaceHold {
  public void setBackground(Color color) {
    checkWidget();
    if (color != null) {
      if (color.isDisposed()) {
        error(ERROR_INVALID_ARGUMENT);
      }
    }
    double[] background = (color != null) ? color.handle : null;
    if (equals(background, this.background)) {
      return;
    }
    this.background = background;
    updateBackgroundColor();
    redrawWidget(view, true);
  }
}
