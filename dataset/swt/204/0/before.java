class PlaceHold {
  void closeWidget(boolean force) {
    Event event = new Event();
    sendEvent(Close, event);
    if ((force || event.doit) && (!isDisposed())) {
      dispose();
    }
  }
}
