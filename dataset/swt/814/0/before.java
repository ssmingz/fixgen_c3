class PlaceHold {
  void addAccelerators() {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      item.addAccelerator();
    }
  }
}
