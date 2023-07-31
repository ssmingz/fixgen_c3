class PlaceHold {
  public Thread getThread() {
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
    return thread;
  }
}
