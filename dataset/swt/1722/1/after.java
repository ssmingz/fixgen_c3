class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    if (timerId != 0) {
      OS.g_source_remove(timerId);
    }
    timerId = 0;
  }
}
