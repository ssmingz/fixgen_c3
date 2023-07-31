class PlaceHold {
  public MenuItem getItem(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    return items[index];
  }
}
