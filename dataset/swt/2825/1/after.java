class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    checkDevice();
    release();
    destroy();
    fGDeviceHandle = 0;
    if (tracking) {
      objects = null;
      errors = null;
    }
  }
}
