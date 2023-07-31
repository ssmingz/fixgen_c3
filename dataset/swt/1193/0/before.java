class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int[] argList =
        new int[] {
          OS.XmNtitleString,
          0,
          OS.XmNborderWidth,
          (style & SWT.BORDER) != 0 ? 1 : 0,
          OS.XmNorientation,
          (style & SWT.H_SCROLL) != 0 ? OS.XmHORIZONTAL : OS.XmVERTICAL,
          OS.XmNprocessingDirection,
          (style & SWT.H_SCROLL) != 0 ? OS.XmMAX_ON_RIGHT : OS.XmMAX_ON_BOTTOM
        };
    handle = OS.XmCreateScale(handle, null, argList, argList.length / 2);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
