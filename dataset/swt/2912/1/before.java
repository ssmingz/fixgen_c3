class PlaceHold {
  void hookEvents() {
    super.hookEvents();
    signal_connect(handle, "clicked", Selection, 2);
  }
}
