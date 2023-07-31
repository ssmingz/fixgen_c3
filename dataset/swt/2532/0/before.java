class PlaceHold {
  public boolean isEmpty() {
    if (isDisposed()) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    return OS.EmptyRgn(handle);
  }
}
