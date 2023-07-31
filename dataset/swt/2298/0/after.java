class PlaceHold {
  void deregister() {
    super.deregister();
    if (focusHandle != 0) {
      display.removeWidget(focusHandle);
    }
  }
}
