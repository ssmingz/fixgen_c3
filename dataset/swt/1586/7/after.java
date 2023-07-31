class PlaceHold {
  int getChildrenCount() {
    long list = OS.gtk_container_get_children(handle);
    if (list == 0) {
      return 0;
    }
    int count = OS.g_list_length(list);
    OS.g_list_free(list);
    return count;
  }
}
