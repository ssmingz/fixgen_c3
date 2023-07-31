class PlaceHold {
  void destroyWidget() {
    if (((OS.gtk_major_version() == 2) && (OS.gtk_minor_version() == 0))
        && (OS.gtk_micro_version() < 5)) {
      while (OS.gtk_events_pending() != 0) {
        OS.gtk_main_iteration();
      }
    }
    super.destroyWidget();
  }
}
