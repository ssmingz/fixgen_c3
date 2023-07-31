class PlaceHold {
  void register() {
    super.register();
    if (scrolledHandle != 0) {
      display.addWidget(scrolledHandle, this);
    }
  }
}
