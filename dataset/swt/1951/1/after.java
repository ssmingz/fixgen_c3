class PlaceHold {
  int findButtonHandle() {
    int result = 0;
    OS.gtk_container_forall(handle, allChildrenProc, 0);
    if (display.allChildren != 0) {
      int list = display.allChildren;
      while (list != 0) {
        int widget = OS.g_list_data(list);
        if (OS.GTK_IS_BUTTON(widget)) {
          result = widget;
          break;
        }
      }
      OS.g_list_free(allChildren);
      display.allChildren = 0;
    }
    return result;
  }
}
