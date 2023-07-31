class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | CANVAS;
    if ((style & (SWT.H_SCROLL | SWT.V_SCROLL)) == 0) {
      int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
      int parentHandle = parent.handle;
      handle = MacUtil.createDrawingArea(parentHandle, -1, true, 0, 0, border);
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
    } else {
      createScrolledHandle(handle);
    }
  }
}
