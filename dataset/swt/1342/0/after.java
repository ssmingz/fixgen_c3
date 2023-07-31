class PlaceHold {
  void destroyWidget() {
    long topHandle = topHandle();
    if (parent != null) {
      parent.removeTooTip(this);
    }
    releaseHandle();
    if ((topHandle != 0) && ((state & HANDLE) != 0)) {
      if ((style & SWT.BALLOON) != 0) {
        OS.gtk_widget_destroy(topHandle);
      } else {
        OS.g_object_unref(topHandle);
      }
    }
  }
}
