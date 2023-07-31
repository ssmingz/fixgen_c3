class PlaceHold {
  MenuItem findMenuItem(int id) {
    if (items == null) {
      return null;
    }
    id -= FIRST_MENU_ITEM_ID;
    if ((0 <= id) && (id < items.length)) {
      return items[id];
    }
    return null;
  }
}
