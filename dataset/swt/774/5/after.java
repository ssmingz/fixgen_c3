class PlaceHold {
  public int indexOf(ToolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    for (int i = 0; i < itemCount; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
