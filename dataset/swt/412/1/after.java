class PlaceHold {
  int gtk_event_after(int widget, int gdkEvent) {
    GdkEvent event = new GdkEvent();
    OS.memmove(event, gdkEvent, sizeof);
    switch (event.type) {
      case OS.GDK_BUTTON_PRESS:
        {
          if ((OS.GTK_VERSION < OS.VERSION(2, 8, 0)) && (!selectionAdded)) {
            int grabHandle = OS.gtk_grab_get_current();
            if (grabHandle != 0) {
              if (OS.G_OBJECT_TYPE(grabHandle) == OS.GTK_TYPE_MENU()) {
                menuHandle = grabHandle;
                OS.g_object_ref(menuHandle);
                OS.g_signal_connect_closure_by_id(
                    menuHandle,
                    display.signalIds[BUTTON_RELEASE_EVENT],
                    0,
                    display.closures[BUTTON_RELEASE_EVENT],
                    false);
                OS.g_signal_connect_closure_by_id(
                    menuHandle,
                    display.signalIds[BUTTON_RELEASE_EVENT],
                    0,
                    display.closures[BUTTON_RELEASE_EVENT_INVERSE],
                    true);
                OS.g_signal_connect_closure(
                    menuHandle, selection_done, display.closures[SELECTION_DONE], false);
                display.addWidget(menuHandle, this);
                selectionAdded = true;
              }
            }
          }
          GdkEventButton gdkEventButton = new GdkEventButton();
          OS.memmove(gdkEventButton, gdkEvent, sizeof);
          if (gdkEventButton.button == 1) {
            if (!sendMouseEvent(
                MouseDown,
                gdkEventButton.button,
                clickCount,
                0,
                false,
                gdkEventButton.time,
                gdkEventButton.x_root,
                gdkEventButton.y_root,
                false,
                gdkEventButton.state)) {
              return 1;
            }
            if (OS.GTK_VERSION >= OS.VERSION(2, 6, 0)) {
              if (((style & SWT.READ_ONLY) == 0) && (widget == buttonHandle)) {
                OS.gtk_widget_grab_focus(entryHandle);
              }
            }
          }
          break;
        }
      case OS.GDK_FOCUS_CHANGE:
        {
          if (OS.GTK_VERSION >= OS.VERSION(2, 6, 0)) {
            if ((style & SWT.READ_ONLY) == 0) {
              GdkEventFocus gdkEventFocus = new GdkEventFocus();
              OS.memmove(gdkEventFocus, gdkEvent, sizeof);
              if (gdkEventFocus.in != 0) {
                OS.gtk_combo_box_set_focus_on_click(handle, false);
              } else {
                OS.gtk_combo_box_set_focus_on_click(handle, true);
              }
            }
          }
          break;
        }
    }
    return super.gtk_event_after(widget, gdkEvent);
  }
}
