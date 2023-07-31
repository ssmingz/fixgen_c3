class PlaceHold {
  public MenuItem getItem(int index) {
    checkWidget();
    int count = OS.CountMenuItems(handle);
    if (!((0 <= index) && (index < count))) {
      error(ERROR_INVALID_RANGE);
    }
    return items[index];
  }
}
