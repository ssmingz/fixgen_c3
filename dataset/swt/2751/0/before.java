class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    handle = 0;
    if ((style & SWT.H_SCROLL) != 0) {
      handle = parent.fHScrollBar;
    } else if ((style & SWT.V_SCROLL) != 0) {
      handle = parent.fVScrollBar;
    }
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
