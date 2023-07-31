class PlaceHold {
  void register() {
    super.register();
    if (focusHandle != 0) {
      display.addWidget(focusHandle, this);
    }
  }
}
