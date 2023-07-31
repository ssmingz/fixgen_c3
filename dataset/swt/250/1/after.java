class PlaceHold {
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    checkDevice();
    release();
    destroy();
    deregister(this);
    xDisplay = 0;
    if (tracking) {
      objects = null;
      errors = null;
    }
  }
}
