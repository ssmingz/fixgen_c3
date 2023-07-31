class PlaceHold {
  int processEvent(int eventNumber, int int0, int int1, int int2) {
    if (eventNumber == 0) {
      GdkEvent gdkEvent = new GdkEvent();
      OS.memmove(gdkEvent, int0, sizeof);
      switch (gdkEvent.type) {
        case OS.GDK_BUTTON_PRESS:
        case OS.GDK_2BUTTON_PRESS:
          {
            OS.GTK_CLIST_RESYNC_SELECTION(handle);
            break;
          }
        case OS.GDK_BUTTON_RELEASE:
          {
            if ((style & SWT.MULTI) != 0) {
              if (selected) {
                double[] px = new double[1];
                double[] py = new double[1];
                OS.gdk_event_get_coords(int0, px, py);
                int x = ((int) (px[0]));
                int y = ((int) (py[0]));
                int[] row = new int[1];
                int[] column = new int[1];
                if (OS.gtk_clist_get_selection_info(handle, x, y, row, column) != 0) {
                  int list = OS.GTK_CLIST_SELECTION(handle);
                  if (list != 0) {
                    int length = OS.g_list_length(list);
                    for (int i = 0; i < length; i++) {
                      if (row[0] == OS.g_list_nth_data(list, i)) {
                        postEvent(Selection);
                      }
                    }
                  }
                }
              }
              selected = false;
            }
            break;
          }
        case OS.GDK_FOCUS_CHANGE:
          {
            GdkEventFocus focusEvent = new GdkEventFocus();
            OS.memmove(focusEvent, int0, sizeof);
            if (focusEvent.in == 0) {
              OS.gtk_grab_remove(handle);
              OS.gdk_pointer_ungrab(0);
            }
            break;
          }
      }
      return 1;
    }
    return super.processEvent(eventNumber, int0, int1, int2);
  }
}
