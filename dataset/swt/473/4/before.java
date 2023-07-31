class PlaceHold {
  public int indexOf(ToolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int count = itemCount;
    for (int i = 0; i < count; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
