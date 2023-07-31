class PlaceHold {
  int XmNactivateCallback(int w, int client_data, int call_data) {
    if ((style & SWT.CASCADE) != 0) {
      sendEvent(Arm);
    }
    if (!isEnabled()) {
      return 0;
    }
    XmAnyCallbackStruct struct = new XmAnyCallbackStruct();
    OS.memmove(struct, call_data, sizeof);
    Event event = new Event();
    if (struct.event != 0) {
      XButtonEvent xEvent = new XButtonEvent();
      OS.memmove(xEvent, struct.event, sizeof);
      event.time = xEvent.time;
      switch (xEvent.type) {
        case OS.ButtonPress:
        case OS.ButtonRelease:
        case OS.KeyPress:
        case OS.KeyRelease:
          setInputState(event, xEvent.state);
          break;
      }
    }
    postEvent(Selection, event);
    return 0;
  }
}
