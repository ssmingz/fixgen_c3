class PlaceHold {
  public void dispose() {
    if (handle == 0) {
      return;
    }
    if (device.isDisposed()) {
      return;
    }
    if (memGC != null) {
      memGC.dispose();
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
