class PlaceHold {
  public int getLineWidth() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    return ((int) (data.lineWidth));
  }
}
