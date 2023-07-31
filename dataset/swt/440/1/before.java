class PlaceHold {
  public int indexOf(TabItem item) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int list = OS.gtk_container_children(notebookHandle);
    int itemCount = OS.g_list_length(list);
    for (int i = 0; i < itemCount; i++) {
      if (items[i] == item) {
        return i;
      }
    }
    return -1;
  }
}
