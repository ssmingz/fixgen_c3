class PlaceHold {
  void deregister() {
    super.deregister();
    if (scrolledHandle != 0) {
      WidgetTable.remove(scrolledHandle);
    }
  }
}
