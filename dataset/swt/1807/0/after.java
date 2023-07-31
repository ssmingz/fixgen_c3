class PlaceHold {
  void destroyWidget() {
    if (OS.GTK_VERSION < OS.VERSION(2, 0, 5)) {
      while (OS.gtk_events_pending() != 0) {
        OS.gtk_main_iteration();
      }
    }
    super.destroyWidget();
  }
}
