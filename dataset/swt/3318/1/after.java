class PlaceHold {
  int gtk_focus_in_event(int widget, int event) {
    if (widget != shellHandle) {
      return super.gtk_focus_in_event(widget, event);
    }
    if (tooltipsHandle != 0) {
      OS.gtk_tooltips_enable(tooltipsHandle);
    }
    hasFocus = true;
    sendEvent(Activate);
    return 0;
  }
}
