class PlaceHold {
  public int getLineJoin() {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    return data.lineJoin;
  }
}
