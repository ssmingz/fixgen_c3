class PlaceHold {
  public int internal_new_GC(GCData data) {
    if (isDisposed()) {
      SWT.error(ERROR_DEVICE_DISPOSED);
    }
    int phGC = OS.PgCreateGC(0);
    if (phGC == 0) {
      SWT.error(ERROR_NO_HANDLES);
    }
    int mask = SWT.LEFT_TO_RIGHT | SWT.RIGHT_TO_LEFT;
    if ((data.style & mask) == 0) {
      data.style |= SWT.LEFT_TO_RIGHT;
    }
    data.device = this;
    data.rid = OS.Ph_DEV_RID;
    return phGC;
  }
}
