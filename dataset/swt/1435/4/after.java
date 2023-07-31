class PlaceHold {
  @Override
  void createHandle(int index) {
    state |= HANDLE | THEME_BACKGROUND;
    handle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_widget_set_has_window(handle, true);
    OS.gtk_widget_set_can_focus(handle, true);
    layout = new TextLayout(display);
    linkColor = display.getSystemColor(COLOR_LINK_FOREGROUND);
    disabledColor = new Color(display, LINK_DISABLED_FOREGROUND);
    offsets = new Point[0];
    ids = new String[0];
    mnemonics = new int[0];
    selection = new Point(-1, -1);
    focusIndex = -1;
  }
}
