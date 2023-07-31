class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if (timerId != 0) {
      OS.gtk_timeout_remove(timerId);
    }
    timerId = 0;
  }
}
