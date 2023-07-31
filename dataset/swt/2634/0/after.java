class PlaceHold {
  int gtk_button_press_event(int widget, int event) {
    Shell shell = _getShell();
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.type == OS.GDK_3BUTTON_PRESS) {
      return 0;
    }
    display.dragStartX = ((int) (gdkEvent.x));
    display.dragStartY = ((int) (gdkEvent.y));
    display.dragging = false;
    int button = gdkEvent.button;
    int type = (gdkEvent.type != OS.GDK_2BUTTON_PRESS) ? SWT.MouseDown : SWT.MouseDoubleClick;
    sendMouseEvent(
        type, button, gdkEvent.time, gdkEvent.x_root, gdkEvent.y_root, gdkEvent.state, event);
    int result = 0;
    if ((state & MENU) != 0) {
      if ((gdkEvent.button == 3) && (gdkEvent.type == OS.GDK_BUTTON_PRESS)) {
        if (showMenu(((int) (gdkEvent.x_root)), ((int) (gdkEvent.y_root)))) {
          result = 1;
        }
      }
    }
    if (!shell.isDisposed()) {
      shell.setActiveControl(this);
    }
    return result;
  }
}
