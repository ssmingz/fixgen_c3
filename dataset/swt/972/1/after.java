class PlaceHold {
  public Thread getThread() {
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      return thread;
    }
  }
}
