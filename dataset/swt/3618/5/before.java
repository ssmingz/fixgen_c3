class PlaceHold {
  public void dispose() {
    if (display.getThread() != Thread.currentThread()) {
      DND.error(ERROR_THREAD_INVALID_ACCESS);
    }
    display = null;
  }
}
