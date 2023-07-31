class PlaceHold {
  public void clear(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    TableItem item = items[index];
    if (item != null) {
      item.clear();
      item.redraw(-1);
    }
  }
}
