class PlaceHold {
  protected void checkWidget() {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
  }
}
