class PlaceHold {
  public void setBackground(Color color) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (color == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (color.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if (transparentPixel == (-1)) {
      return;
    }
  }
}
