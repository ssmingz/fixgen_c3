class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int[] argList =
        new int[] {
          OS.XmNborderWidth,
          (style & SWT.BORDER) != 0 ? 1 : 0,
          OS.XmNorientation,
          (style & SWT.H_SCROLL) != 0 ? OS.XmHORIZONTAL : OS.XmVERTICAL
        };
    handle = OS.XmCreateScrollBar(handle, null, argList, argList.length / 2);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
