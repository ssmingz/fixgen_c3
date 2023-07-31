class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    signal_connect(handle, "value_changed", Selection, 2);
  }
}
