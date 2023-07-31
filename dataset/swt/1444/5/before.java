class PlaceHold {
  void register() {
    super.register();
    if (focusHandle != 0) {
      WidgetTable.put(focusHandle, this);
    }
  }
}
