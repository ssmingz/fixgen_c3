class PlaceHold {
  int processMouseDown(int callData, int arg1, int int2) {
    Shell shell = _getShell();
    int type = OS.GDK_EVENT_TYPE(callData);
    int eventType = (type != OS.GDK_2BUTTON_PRESS) ? SWT.MouseDown : SWT.MouseDoubleClick;
    int button = OS.gdk_event_button_get_button(callData);
    sendMouseEvent(eventType, button, callData);
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    return 0;
  }
}
