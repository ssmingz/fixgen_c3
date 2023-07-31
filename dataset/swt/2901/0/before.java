class PlaceHold {
  int processSelection(int int0, int int1, int int2) {
    if ((style & SWT.RADIO) != 0) {
      this.setSelection(true);
      ToolItem[] items = parent.getItems();
      int index = 0;
      while ((index < items.length) && (items[index] != this)) {
        index++;
      }
      ToolItem item;
      int i = index;
      while (((--i) >= 0) && (((item = items[i]).style & SWT.RADIO) != 0)) {
        item.setSelection(false);
      }
      i = index;
      while (((++i) < items.length) && (((item = items[i]).style & SWT.RADIO) != 0)) {
        item.setSelection(false);
      }
    }
    Event event = new Event();
    if ((style & SWT.DROP_DOWN) != 0) {
      int eventPtr = OS.gtk_get_current_event();
      if (eventPtr != 0) {
        GdkEvent gdkEvent = new GdkEvent();
        OS.memmove(gdkEvent, eventPtr, sizeof);
        switch (gdkEvent.type) {
          case OS.GDK_BUTTON_PRESS:
          case OS.GDK_2BUTTON_PRESS:
          case OS.GDK_BUTTON_RELEASE:
            {
              double[] x_win = new double[1];
              double[] y_win = new double[1];
              OS.gdk_event_get_coords(eventPtr, x_win, y_win);
              if (((int) (x_win[0])) > OS.GTK_WIDGET_WIDTH(boxHandle)) {
                event.detail = SWT.ARROW;
              }
              break;
            }
        }
      }
    }
    postEvent(Selection, event);
    return 0;
  }
}
