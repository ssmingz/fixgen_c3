class PlaceHold {
  public TabItem getItem(int index) {
    checkWidget();
    int list = OS.gtk_container_children(handle);
    int itemCount = OS.g_list_length(list);
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return items[index];
  }
}
