class PlaceHold {
  void deregister() {
    super.deregister();
    display.removeWidget(handle);
    if (buttonHandle != 0) {
      display.removeWidget(buttonHandle);
    }
    if (labelHandle != 0) {
      display.removeWidget(labelHandle);
    }
  }
}
