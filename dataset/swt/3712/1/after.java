class PlaceHold {
  public MenuItem getItem(int index) {
    checkWidget();
    int list = OS.gtk_container_get_children(handle);
    if (list == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    int data = OS.g_list_nth_data(list, index);
    OS.g_list_free(list);
    if (data == 0) {
      error(ERROR_CANNOT_GET_ITEM);
    }
    return ((MenuItem) (WidgetTable.get(data)));
  }
}
