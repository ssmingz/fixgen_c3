class PlaceHold {
  void addAccelerators(int accelGroup) {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      item.addAccelerator(accelGroup);
    }
  }
}
