class PlaceHold {
  public Thread getSyncThread() {
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
    return synchronizer.syncThread;
  }
}
