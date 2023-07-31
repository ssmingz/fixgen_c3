class PlaceHold {
  void gdk_pointer_ungrab(long window, int time_) {
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      long display = OS.gdk_window_get_display(window);
      long device_manager = OS.gdk_display_get_device_manager(display);
      long pointer = OS.gdk_device_manager_get_client_pointer(device_manager);
      OS.gdk_device_ungrab(pointer, time_);
    } else {
      OS.gdk_pointer_ungrab(time_);
    }
  }
}
