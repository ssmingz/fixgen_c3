class PlaceHold {
  public int indexOf(ToolItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    ToolItem[] items = getItems();
    for (int i = 0; i < items.length; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
