class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int[] argList =
        new int[] {
          OS.XmNancestorSensitive,
          1,
          OS.XmNwidth,
          MINIMUM_WIDTH,
          OS.XmNheight,
          DEFAULT_HEIGHT,
          OS.XmNpositionIndex,
          index,
          OS.XmNtraversalOn,
          0
        };
    int parentHandle = parent.handle;
    handle = OS.XmCreateDrawingArea(parentHandle, null, argList, argList.length / 2);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
