class PlaceHold {
  protected void checkWidget() {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (isDisposed()) {
      error(ERROR_WIDGET_DISPOSED);
    }
  }
}
