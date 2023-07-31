class PlaceHold {
  int processEvent(int eventNumber, int int0, int int1, int int2) {
    if (eventNumber == 0) {
      GdkEvent gdkEvent = new GdkEvent();
      OS.memmove(gdkEvent, int0, sizeof);
      int type = gdkEvent.type;
      switch (type) {
        case OS.GDK_BUTTON_PRESS:
        case OS.GDK_2BUTTON_PRESS:
          {
            OS.GTK_CLIST_RESYNC_SELECTION(handle);
            if (type == OS.GDK_2BUTTON_PRESS) {
              doubleSelected = true;
              double[] px = new double[1];
              double[] py = new double[1];
              OS.gdk_event_get_coords(int0, px, py);
              int x = ((int) (px[0]));
              int y = ((int) (py[0]));
              if (!OS.gtk_ctree_is_hot_spot(handle, x, y)) {
                int[] row = new int[1];
                int[] column = new int[1];
                int code = OS.gtk_clist_get_selection_info(handle, x, y, row, column);
                if (code != 0) {
                  int node = OS.gtk_ctree_node_nth(handle, row[0]);
                  int index = OS.gtk_ctree_node_get_row_data(handle, node) - 1;
                  Event event = new Event();
                  event.item = items[index];
                  postEvent(DefaultSelection, event);
                }
              }
            }
            break;
          }
        case OS.GDK_BUTTON_RELEASE:
          {
            if ((style & SWT.MULTI) != 0) {
              if (selected && (!doubleSelected)) {
                double[] px = new double[1];
                double[] py = new double[1];
                OS.gdk_event_get_coords(int0, px, py);
                int x = ((int) (px[0]));
                int y = ((int) (py[0]));
                if (!OS.gtk_ctree_is_hot_spot(handle, x, y)) {
                  int[] row = new int[1];
                  int[] column = new int[1];
                  int code = OS.gtk_clist_get_selection_info(handle, x, y, row, column);
                  if (code != 0) {
                    int focus = OS.gtk_ctree_node_nth(handle, row[0]);
                    int selection = OS.GTK_CLIST_SELECTION(handle);
                    if (selection != 0) {
                      int length = OS.g_list_length(selection);
                      for (int i = 0; i < length; i++) {
                        int node = OS.g_list_nth_data(selection, i);
                        if (node == focus) {
                          int index = OS.gtk_ctree_node_get_row_data(handle, node) - 1;
                          Event event = new Event();
                          event.item = items[index];
                          postEvent(Selection, event);
                        }
                      }
                    }
                  }
                }
              }
              selected = false;
            }
            doubleSelected = false;
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
