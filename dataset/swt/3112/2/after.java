class PlaceHold {
  long gtk_popup_menu(long widget) {
    if (!hasFocus()) {
      return 0;
    }
    int[] x = new int[1];
    int[] y = new int[1];
    gdk_window_get_device_position(0, x, y, null);
    return showMenu(x[0], y[0], MENU_KEYBOARD) ? 1 : 0;
  }
}
