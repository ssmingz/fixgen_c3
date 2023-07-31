class PlaceHold {
  MenuItem findMenuItem(int id) {
    if (items == null) {
      return null;
    }
    id -= 1000;
    if ((0 <= id) && (id < items.length)) {
      return items[id];
    }
    return null;
  }
}
