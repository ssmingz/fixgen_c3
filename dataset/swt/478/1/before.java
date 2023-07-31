class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    int[] argList =
        new int[] {
          OS.XmNwidth,
          MINIMUM_WIDTH,
          OS.XmNheight,
          DEFAULT_HEIGHT,
          OS.XmNpositionIndex,
          index,
          OS.XmNtraversalOn,
          0
        };
    handle = OS.XmCreateDrawingArea(parent.handle, null, argList, argList.length / 2);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
  }
}
