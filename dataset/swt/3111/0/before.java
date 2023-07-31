class PlaceHold {
  public void translate(int x, int y) {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    OS.OffsetRgn(handle, ((short) (x)), ((short) (y)));
  }
}
