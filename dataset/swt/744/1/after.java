class PlaceHold {
  void showWidget() {
    state |= ZERO_WIDTH | ZERO_HEIGHT;
    int topHandle = topHandle();
    int parentHandle = parent.parentingHandle();
    OS.gtk_container_add(parentHandle, topHandle);
    if ((handle != 0) && (handle != topHandle)) {
      OS.gtk_widget_show(handle);
    }
    if ((state & (ZERO_WIDTH | ZERO_HEIGHT)) == 0) {
      if (fixedHandle != 0) {
        OS.gtk_widget_show(fixedHandle);
      }
    }
    if (fixedHandle != 0) {
      fixStyle(fixedHandle);
    }
  }
}
