class PlaceHold {
  void removeAccelerators(int accelGroup) {
    MenuItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      MenuItem item = items[i];
      item.removeAccelerators(accelGroup);
    }
  }
}
