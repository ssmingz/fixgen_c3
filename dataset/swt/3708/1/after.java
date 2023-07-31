class PlaceHold {
  int gtk_button_press_event(int widget, int event) {
    GdkEventButton gdkEvent = new GdkEventButton();
    OS.memmove(gdkEvent, event, sizeof);
    if (gdkEvent.type == OS.GDK_3BUTTON_PRESS) {
      return 0;
    }
    Shell shell = _getShell();
    if ((shell.style & SWT.ON_TOP) != 0) {
      shell.forceActive();
    }
    display.dragStartX = ((int) (gdkEvent.x));
    display.dragStartY = ((int) (gdkEvent.y));
    display.dragging = false;
    int type = (gdkEvent.type != OS.GDK_2BUTTON_PRESS) ? SWT.MouseDown : SWT.MouseDoubleClick;
    int result =
        (sendMouseEvent(
                type,
                gdkEvent.button,
                gdkEvent.time,
                gdkEvent.x_root,
                gdkEvent.y_root,
                false,
                gdkEvent.state))
            ? 0
            : 1;
    if (isDisposed()) {
      return 1;
    }
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
