class PlaceHold {
  void reskinChildren(int flags) {
    if (items != null) {
      int list = OS.gtk_container_get_children(handle);
      if (list != 0) {
        int count = OS.g_list_length(list);
        OS.g_list_free(list);
        for (int i = 0; i < count; i++) {
          TabItem item = items[i];
          if (item != null) {
            item.reskin(flags);
          }
        }
      }
    }
    super.reskinChildren(flags);
  }
}
