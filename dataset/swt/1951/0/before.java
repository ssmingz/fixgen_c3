class PlaceHold {
  void findMenuHandle() {
    OS.gtk_container_forall(popupHandle, allChildrenProc, 0);
    if (display.allChildren != 0) {
      int list = display.allChildren;
      while (list != 0) {
        int widget = OS.g_list_data(list);
        if (OS.G_OBJECT_TYPE(widget) == OS.GTK_TYPE_MENU()) {
          menuHandle = widget;
          OS.g_object_ref(menuHandle);
          break;
        }
        list = OS.g_list_next(list);
      }
      OS.g_list_free(allChildren);
      display.allChildren = 0;
    }
  }
}
