class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    destroyImage(handle);
    handle = 0;
    memGC = null;
    if (device.tracking) {
      device.dispose_Object(this);
    }
    device = null;
  }
}
