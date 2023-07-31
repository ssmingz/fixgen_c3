class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE;
    if ((style & SWT.BAR) != 0) {
      handle = OS.gtk_menu_bar_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      int vboxHandle = parent.vboxHandle;
      OS.gtk_container_add(vboxHandle, handle);
      OS.gtk_box_set_child_packing(vboxHandle, handle, false, true, 0, GTK_PACK_START);
    } else {
      handle = OS.gtk_menu_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
    }
  }
}
