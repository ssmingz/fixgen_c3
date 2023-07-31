class PlaceHold {
  boolean sendKeyEvent(int type, XKeyEvent xEvent) {
    Event event = new Event();
    event.time = xEvent.time;
    setKeyState(event, xEvent);
    Control control = this;
    if ((state & CANVAS) != 0) {
      if ((style & SWT.NO_FOCUS) != 0) {
        control = display.getFocusControl();
      }
    }
    if (control != null) {
      control.sendEvent(type, event);
      if (isDisposed()) {
        return false;
      }
    }
    return event.doit;
  }
}
