class PlaceHold {
  public TabItem getItem(Point point) {
    checkWidget();
    if (point == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    int list = OS.gtk_container_get_children(handle);
    if (list == 0) {
      return null;
    }
    int itemCount = OS.g_list_length(list);
    OS.g_list_free(list);
    for (int i = 0; i < itemCount; i++) {
      TabItem item = items[i];
      Rectangle rect = item.getBounds();
      if (rect.contains(point)) {
        return item;
      }
    }
    return null;
  }
}
