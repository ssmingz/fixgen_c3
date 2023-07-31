class PlaceHold {
  int processMouseUp(int callData, int arg1, int int2) {
    int[] pMod = new int[1];
    OS.gdk_event_get_state(callData, pMod);
    int time = OS.gdk_event_get_time(callData);
    double[] px = new double[1];
    double[] py = new double[1];
    OS.gdk_event_get_coords(callData, px, py);
    int button = OS.gdk_event_button_get_button(callData);
    sendMouseEvent(MouseUp, button, pMod[0], time, ((int) (px[0])), ((int) (py[0])));
    return 0;
  }
}
