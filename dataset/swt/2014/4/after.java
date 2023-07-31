class PlaceHold {
  void showHandle() {
    OS.gtk_widget_show(fixedHandle);
    OS.gtk_widget_show(scrolledHandle);
    OS.gtk_widget_show(handle);
    OS.gtk_widget_realize(handle);
  }
}
