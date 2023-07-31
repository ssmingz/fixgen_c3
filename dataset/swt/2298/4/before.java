class PlaceHold {
  void deregister() {
    super.deregister();
    WidgetTable.remove(shellHandle);
  }
}
