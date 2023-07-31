class PlaceHold {
  protected void checkDevice() {
    if (thread == null) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (thread != Thread.currentThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
  }
}
