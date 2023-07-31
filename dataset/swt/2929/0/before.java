class PlaceHold {
  void remove(MenuItem item) {
    if (items == null) {
      return;
    }
    items[item.id - 1000] = null;
    item.id = -1;
  }
}
