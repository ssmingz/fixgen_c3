class PlaceHold {
  int findMenuHandle() {
    if (popupHandle == 0) {
      return 0;
    }
    int result = 0;
    OS.gtk_container_forall(popupHandle, allChildrenProc, 0);
    if (display.allChildren != 0) {
      int list = display.allChildren;
      while (list != 0) {
        int widget = OS.g_list_data(list);
        if (OS.G_OBJECT_TYPE(widget) == OS.GTK_TYPE_MENU()) {
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
