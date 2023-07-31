class PlaceHold {
  void closeWidget(boolean force) {
    if (display.isDisposed()) {
      return;
    }
    Event event = new Event();
    sendEvent(Close, event);
    if ((force || event.doit) && (!isDisposed())) {
      dispose();
    }
  }
}
