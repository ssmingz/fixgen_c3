class PlaceHold {
  public void wake() {
    if (isDisposed()) {
      error(ERROR_DEVICE_DISPOSED);
    }
    OS.PostThreadMessage(threadId, WM_NULL, 0, 0);
  }
}
