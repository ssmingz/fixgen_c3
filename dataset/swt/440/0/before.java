class PlaceHold {
  public TabItem getItem(int index) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int list = OS.gtk_container_children(notebookHandle);
    int itemCount = OS.g_list_length(list);
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return items[index];
  }
}
