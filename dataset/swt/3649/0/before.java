class PlaceHold {
  public int indexOf(TabItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int count = getItemCount();
    for (int i = 0; i < count; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
