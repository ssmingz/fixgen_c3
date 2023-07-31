class PlaceHold {
  int gtk_focus_out_event(int widget, int event) {
    if (widget != shellHandle) {
      return super.gtk_focus_out_event(widget, event);
    }
    if (tooltipsHandle != 0) {
      OS.gtk_tooltips_disable(tooltipsHandle);
    }
    hasFocus = false;
    sendEvent(Deactivate);
    return 0;
  }
}
