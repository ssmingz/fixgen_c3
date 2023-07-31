class PlaceHold {
  public MenuItem getItem(int index) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int list = OS.gtk_container_children(handle);
    int data = OS.g_list_nth_data(list, index);
    if (data == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return ((MenuItem) (WidgetTable.get(data)));
  }
}
