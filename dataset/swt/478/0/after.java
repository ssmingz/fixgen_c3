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
          OS.XmRESIZE_NONE,
          OS.XmNancestorSensitive,
          1
        };
    int parentHandle = parent.handle;
    handle = OS.XmCreateDrawingArea(parentHandle, null, argList, argList.length / 2);
  }
}
