class PlaceHold {
  void remove(MenuItem item) {
    if (items == null) {
      return;
    }
    items[item.id - FIRST_MENU_ITEM_ID] = null;
    item.id = -1;
  }
}
