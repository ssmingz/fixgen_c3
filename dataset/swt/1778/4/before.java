class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (type == SWT.ICON) {
      if (OS.IsWinCE) {
        data = null;
      }
      OS.DestroyIcon(handle);
    } else {
      OS.DeleteObject(handle);
    }
    handle = 0;
    memGC = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}
