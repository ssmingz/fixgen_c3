class PlaceHold {
  void putGdkEvents() {
    if (gdkEventCount != 0) {
      for (int i = 0; i < gdkEventCount; i++) {
        int event = gdkEvents[i];
        Widget widget = gdkEventWidgets[i];
        if ((widget == null) || (!widget.isDisposed())) {
          OS.gdk_event_put(event);
        }
        OS.gdk_event_free(event);
        gdkEvents[i] = 0;
        gdkEventWidgets[i] = null;
      }
      gdkEventCount = 0;
    }
  }
}
