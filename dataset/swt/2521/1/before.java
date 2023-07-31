class PlaceHold {
  void showWidget(int index) {
    if (handle != 0) {
      OS.gtk_widget_show(handle);
    }
    if (((style & SWT.DROP_DOWN) != 0) && (OS.GTK_VERSION < OS.VERSION(2, 6, 0))) {
      if (arrowBoxHandle != 0) {
        OS.gtk_widget_show(arrowBoxHandle);
      }
      if (arrowHandle != 0) {
        OS.gtk_widget_show(arrowHandle);
      }
    }
    OS.gtk_toolbar_insert(parent.handle, handle, index);
  }
}
