class PlaceHold {
  void hookEvents() {
    if ((style & SWT.BALLOON) != 0) {
      OS.g_signal_connect_closure(handle, expose_event, display.closures[EXPOSE_EVENT], false);
      OS.gtk_widget_add_events(handle, GDK_BUTTON_PRESS_MASK);
      OS.g_signal_connect_closure(
          handle, button_press_event, display.closures[BUTTON_PRESS_EVENT], false);
    } else if (OS.GTK_VERSION < OS.VERSION(2, 12, 0)) {
      int tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(handle);
      if (tipWindow != 0) {
        OS.g_signal_connect_closure(
            tipWindow, size_allocate, display.closures[SIZE_ALLOCATE], false);
        OS.gtk_widget_add_events(tipWindow, GDK_BUTTON_PRESS_MASK);
        OS.g_signal_connect_closure(
            tipWindow, button_press_event, display.closures[BUTTON_PRESS_EVENT], false);
      }
    }
  }
}
