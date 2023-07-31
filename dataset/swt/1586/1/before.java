class PlaceHold {
  public int getItemCount() {
    checkWidget();
    int list = OS.gtk_container_get_children(handle);
    if (list == 0) {
      return 0;
    }
    int itemCount = OS.g_list_length(list);
    OS.g_list_free(list);
    return itemCount;
  }
}
