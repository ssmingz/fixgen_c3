class PlaceHold {
  void createHandle(int index) {
    if ((style & SWT.BALLOON) != 0) {
      state |= HANDLE;
      handle = OS.gtk_window_new(GTK_WINDOW_POPUP);
      Color background = display.getSystemColor(COLOR_INFO_BACKGROUND);
      if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
        GdkColor color = background.handle;
        GdkRGBA rgba = new GdkRGBA();
        rgba.alpha = 1;
        rgba.red = (color.red & 0xffff) / ((float) (0xffff));
        rgba.green = (color.green & 0xffff) / ((float) (0xffff));
        rgba.blue = (color.blue & 0xffff) / ((float) (0xffff));
        OS.gtk_widget_override_background_color(handle, GTK_STATE_FLAG_NORMAL, rgba);
      } else {
        OS.gtk_widget_modify_bg(handle, GTK_STATE_NORMAL, background.handle);
      }
      OS.gtk_widget_set_app_paintable(handle, true);
      OS.gtk_window_set_type_hint(handle, GDK_WINDOW_TYPE_HINT_TOOLTIP);
    } else if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
      state |= HANDLE;
      handle = OS.gtk_tooltips_new();
      if (handle == 0) {
        error(ERROR_NO_HANDLES);
      }
      OS.gtk_tooltips_force_window(handle);
      OS.g_object_ref(handle);
      g_object_ref_sink(handle);
    }
  }
}
