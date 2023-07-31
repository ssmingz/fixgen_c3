class PlaceHold {
  int processMouseDown(int callData, int arg1, int int2) {
    int result = super.processMouseDown(callData, arg1, int2);
    OS.gtk_widget_grab_focus(handle);
    boolean isDoubleClick = OS.gdk_event_button_get_button(callData) == 2;
    int[] ppx = new int[1];
    int[] ppy = new int[1];
    OS.gdk_window_get_pointer(OS.GTK_WIDGET_WINDOW(handle), ppx, ppy, 0);
    int eventType;
    if (isDoubleClick) {
      eventType = SWT.MouseDoubleClick;
      Event event = new Event();
      event.item = itemBeingSelected;
      event.x = ppx[0];
      event.y = ppy[0];
      sendEvent(DefaultSelection, event);
      return result;
    }
    eventType = SWT.MouseDown;
    int[] pMod = new int[1];
    OS.gdk_event_get_state(callData, pMod);
    int time = OS.gdk_event_get_time(callData);
    double[] px = new double[1];
    double[] py = new double[1];
    OS.gdk_event_get_coords(callData, px, py);
    int button = OS.gdk_event_button_get_button(callData);
    sendMouseEvent(eventType, button, pMod[0], time, ((int) (px[0])), ((int) (py[0])));
    if ((button == 3) && (menu != null)) {
      menu.setVisible(true);
    }
    if ((style & SWT.CHECK) != 0) {
      GtkCList clist = new GtkCList(handle);
      int clientX = ppx[0];
      int clientY = ppy[0] - clist.column_title_area_height;
      if (clientY <= 0) {
        return result;
      }
      int[] row = new int[1];
      int[] column = new int[1];
      row[0] = -1;
      OS.gtk_clist_get_selection_info(handle, clientX, clientY, row, column);
      if (row[0] == (-1)) {
        return result;
      }
      int leftmost = 2;
      if (clientX < leftmost) {
        return result;
      }
      if (clientX > (leftmost + check_width)) {
        return result;
      }
      TableItem item = items[row[0]];
      item.setChecked(!item.getChecked());
      Event event = new Event();
      event.detail = SWT.CHECK;
      event.item = item;
      postEvent(Selection, event);
    }
    return result;
  }
}
