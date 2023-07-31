class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (handle == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    if (data != null) {
      if (isGCCreated) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
      if ((data.style & mask) != 0) {
        data.layout = ((data.style & SWT.RIGHT_TO_LEFT) != 0) ? OS.LAYOUT_RTL : 0;
      } else {
        data.style |= SWT.LEFT_TO_RIGHT;
      }
      data.device = this;
      data.hFont = OS.GetCurrentObject(handle, OBJ_FONT);
      isGCCreated = true;
    }
    return handle;
  }
}
