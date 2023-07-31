class PlaceHold {
  void releaseWidget() {
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    items = null;
    if (check != 0) {
      OS.g_object_unref(check);
    }
    if (uncheck != 0) {
      OS.g_object_unref(uncheck);
    }
    check = uncheck = 0;
    if (timerID != 0) {
      OS.gtk_timeout_remove(timerID);
    }
    super.releaseWidget();
  }
}
