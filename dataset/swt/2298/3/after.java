class PlaceHold {
  void deregister() {
    super.deregister();
    if (scrolledHandle != 0) {
      display.removeWidget(scrolledHandle);
    }
  }
}
