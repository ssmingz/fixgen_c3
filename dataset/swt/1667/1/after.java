class PlaceHold {
  int mouseHoverProc(int handle) {
    try {
      OS.gdk_threads_enter();
      Widget widget = getWidget(handle);
      if (widget == null) {
        return 0;
      }
      return widget.hoverProc(handle);
    } finally {
      OS.gdk_threads_leave();
    }
  }
}
