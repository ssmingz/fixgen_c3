class PlaceHold {
  public TabItem getItem(int index) {
    checkWidget();
    if (!((0 <= index) && (index < getItemCount()))) {
      error(ERROR_INVALID_RANGE);
    }
    int list = OS.gtk_container_get_children(handle);
    if (list == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    int itemCount = OS.g_list_length(list);
    OS.g_list_free(list);
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return items[index];
  }
}
