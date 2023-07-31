class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    handle = 0;
    if ((style & SWT.H_SCROLL) != 0) {
      handle = parent.hScrollBar;
    } else if ((style & SWT.V_SCROLL) != 0) {
      handle = parent.vScrollBar;
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
