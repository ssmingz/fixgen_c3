class PlaceHold {
  public int getLineCap() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    return data.lineCap;
  }
}
