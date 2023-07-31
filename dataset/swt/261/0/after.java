class PlaceHold {
  protected void checkWidget() {
    Display display = this.display;
    if (display == null) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if ((display.thread != Thread.currentThread()) && (!display.isEmbedded)) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if ((state & DISPOSED) != 0) {
      error(ERROR_WIDGET_DISPOSED);
    }
  }
}
