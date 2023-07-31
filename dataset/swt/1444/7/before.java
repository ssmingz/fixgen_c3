class PlaceHold {
  void register() {
    super.register();
    if (scrolledHandle != 0) {
      WidgetTable.put(scrolledHandle, this);
    }
  }
}
