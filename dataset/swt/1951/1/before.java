class PlaceHold {
  void findButtonHandle() {
    OS.gtk_container_forall(handle, allChildrenProc, 0);
    if (display.allChildren != 0) {
      int list = display.allChildren;
      while (list != 0) {
        int widget = OS.g_list_data(list);
        if (OS.GTK_IS_BUTTON(widget)) {
          buttonHandle = widget;
          OS.g_object_ref(buttonHandle);
          break;
        }
        list = OS.g_list_next(list);
      }
      OS.g_list_free(allChildren);
      display.allChildren = 0;
    }
  }
}
