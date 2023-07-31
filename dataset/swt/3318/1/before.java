class PlaceHold {
  int gtk_focus_in_event(int widget, int event) {
    int result = super.gtk_focus_in_event(widget, event);
    if (handle == 0) {
      return 0;
    }
    if (widget == shellHandle) {
      if (tooltipsHandle != 0) {
        OS.gtk_tooltips_enable(tooltipsHandle);
      }
      hasFocus = true;
      sendEvent(Activate);
      return 0;
    }
    return result;
  }
}
