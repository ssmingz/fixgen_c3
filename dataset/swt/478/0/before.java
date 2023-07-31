class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int border = ((style & SWT.BORDER) != 0) ? 1 : 0;
    int[] argList =
        new int[] {
          OS.XmNborderWidth,
          border,
          OS.XmNmarginWidth,
          0,
          OS.XmNmarginHeight,
          0,
          OS.XmNresizePolicy,
          OS.XmRESIZE_NONE
        };
    handle = OS.XmCreateDrawingArea(handle, null, argList, argList.length / 2);
  }
}
