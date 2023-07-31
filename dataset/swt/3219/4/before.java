class PlaceHold {
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    handle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    gtk_widget_set_has_window(handle, true);
    OS.GTK_WIDGET_SET_FLAGS(handle, GTK_CAN_FOCUS);
    int type = ((style & SWT.VERTICAL) != 0) ? OS.GDK_SB_H_DOUBLE_ARROW : OS.GDK_SB_V_DOUBLE_ARROW;
    defaultCursor = OS.gdk_cursor_new(type);
  }
}
