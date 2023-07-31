class PlaceHold {
  long findButtonHandle() {
    long result = 0;
    OS.gtk_container_forall(handle, allChildrenProc, 0);
    if (display.allChildren != 0) {
      long list = display.allChildren;
      while (list != 0) {
        long widget = OS.g_list_data(list);
        if (OS.GTK_IS_BUTTON(widget)) {
          result = widget;
          break;
        }
        list = OS.g_list_next(list);
      }
      OS.g_list_free(allChildren);
      display.allChildren = 0;
    }
    return result;
  }
}
