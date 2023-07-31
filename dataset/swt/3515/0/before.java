class PlaceHold {
  int timerProc(int widget) {
    OS.gtk_progress_bar_pulse(handle);
    return 1;
  }
}
