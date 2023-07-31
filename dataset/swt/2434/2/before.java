class PlaceHold {
  int processMouseDown(int callData, int arg1, int int2) {
    int type = OS.GDK_EVENT_TYPE(callData);
    int eventType = (type != OS.GDK_2BUTTON_PRESS) ? SWT.MouseDown : SWT.MouseDoubleClick;
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
    return 0;
  }
}
