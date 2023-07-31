class PlaceHold {
  void setToolTipText(int rootWidget, int tipWidget, String string) {
    if (OS.GTK_VERSION >= OS.VERSION(2, 12, 0)) {
      byte[] buffer = null;
      if ((string != null) && (string.length() > 0)) {
        char[] chars = fixMnemonic(string, false);
        buffer = Converter.wcsToMbcs(null, chars, true);
      }
      int oldTooltip = OS.gtk_widget_get_tooltip_text(rootWidget);
      boolean same = false;
      if ((buffer == null) && (oldTooltip == 0)) {
        same = true;
      } else if ((buffer != null) && (oldTooltip != 0)) {
        same = OS.strcmp(oldTooltip, buffer) == 0;
      }
      if (oldTooltip != 0) {
        OS.g_free(oldTooltip);
      }
      if (same) {
        return;
      }
      OS.gtk_widget_set_tooltip_text(rootWidget, null);
      int eventPtr = 0;
      int tipWindow = OS.GTK_WIDGET_WINDOW(rootWidget);
      if (tipWindow != 0) {
        int[] x = new int[1];
        int[] y = new int[1];
        int window = OS.gdk_window_at_pointer(x, y);
        int[] user_data = new int[1];
        if (window != 0) {
          OS.gdk_window_get_user_data(window, user_data);
        }
        if (tipWidget == user_data[0]) {
          eventPtr = OS.gdk_event_new(GDK_MOTION_NOTIFY);
          GdkEventMotion event = new GdkEventMotion();
          event.type = OS.GDK_MOTION_NOTIFY;
          event.window = OS.g_object_ref(tipWindow);
          event.x = x[0];
          event.y = y[0];
          OS.gdk_window_get_origin(window, x, y);
          event.x_root = event.x + x[0];
          event.y_root = event.y + y[0];
          OS.memmove(eventPtr, event, sizeof);
          OS.gtk_main_do_event(eventPtr);
        }
      }
      OS.gtk_widget_set_tooltip_text(rootWidget, buffer);
      if (eventPtr != 0) {
        OS.gtk_main_do_event(eventPtr);
        OS.gdk_event_free(eventPtr);
      }
    } else {
      byte[] buffer = null;
      if ((string != null) && (string.length() > 0)) {
        char[] chars = fixMnemonic(string, false);
        buffer = Converter.wcsToMbcs(null, chars, true);
      }
      int tipData = OS.gtk_tooltips_data_get(tipWidget);
      if (tipData != 0) {
        int oldTooltip = OS.GTK_TOOLTIPS_GET_TIP_TEXT(tipData);
        if ((string == null) && (oldTooltip == 0)) {
          return;
        } else if ((string != null) && (oldTooltip != 0)) {
          if (buffer != null) {
            if (OS.strcmp(oldTooltip, buffer) == 0) {
              return;
            }
          }
        }
      }
      if (tooltipsHandle == 0) {
        tooltipsHandle = OS.gtk_tooltips_new();
        if (tooltipsHandle == 0) {
          error(ERROR_NO_HANDLES);
        }
        OS.g_object_ref(tooltipsHandle);
        OS.gtk_object_sink(tooltipsHandle);
      }
      OS.gtk_tooltips_force_window(tooltipsHandle);
      int tipWindow = OS.GTK_TOOLTIPS_TIP_WINDOW(tooltipsHandle);
      if ((tipWindow != 0) && (tipWindow != tooltipWindow)) {
        OS.g_signal_connect(tipWindow, size_allocate, sizeAllocateProc, shellHandle);
        tooltipWindow = tipWindow;
      }
      boolean set = true;
      if (tipWindow != 0) {
        if ((OS.GTK_WIDGET_FLAGS(tipWidget) & (OS.GTK_REALIZED | OS.GTK_VISIBLE)) != 0) {
          int[] x = new int[1];
          int[] y = new int[1];
          int window = OS.gdk_window_at_pointer(x, y);
          if (window != 0) {
            int[] user_data = new int[1];
            OS.gdk_window_get_user_data(window, user_data);
            if (tipWidget == user_data[0]) {
              set = false;
              int handler_id =
                  OS.g_signal_connect(tipWindow, size_request, sizeRequestProc, shellHandle);
              OS.gtk_tooltips_set_tip(tooltipsHandle, tipWidget, buffer, null);
              OS.gtk_widget_hide(tipWindow);
              int data = OS.gtk_tooltips_data_get(tipWidget);
              OS.GTK_TOOLTIPS_SET_ACTIVE(tooltipsHandle, data);
              OS.gtk_tooltips_set_tip(tooltipsHandle, tipWidget, buffer, null);
              if (handler_id != 0) {
                OS.g_signal_handler_disconnect(tipWindow, handler_id);
              }
            }
          }
        }
      }
      if (set) {
        OS.gtk_tooltips_set_tip(tooltipsHandle, tipWidget, buffer, null);
      }
    }
  }
}
