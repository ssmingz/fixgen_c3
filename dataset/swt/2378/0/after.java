class PlaceHold {
  public Color getBackground() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (transparentPixel == (-1)) {
      return null;
    }
    return null;
  }
}
