class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
    handle = MacUtil.createDrawingArea(handle, -1, true, 0, 0, border);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
