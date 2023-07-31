class PlaceHold {
  public int indexOf(MenuItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    for (int i = 0; i < itemCount; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
