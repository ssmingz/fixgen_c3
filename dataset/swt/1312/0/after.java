class PlaceHold {
  public Thread getSyncThread() {
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      return synchronizer.syncThread;
    }
  }
}
