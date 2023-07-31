class PlaceHold {
  int timerProc(int widget) {
    if (isVisible()) {
      OS.gtk_progress_bar_pulse(handle);
    }
    return 1;
  }
}
