class PlaceHold {
  public int indexOf(TabItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int list = OS.gtk_container_children(handle);
    int itemCount = OS.g_list_length(list);
    for (int i = 0; i < itemCount; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
