class Region {
  public Region() {
    handle = OS.CreateRectRgn(0, 0, 0, 0);
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
  }
}
