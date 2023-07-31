class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    OS.DeleteObject(handle);
    handle = 0;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}
