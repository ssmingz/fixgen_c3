class PlaceHold {
  void moveHandle(int x, int y) {
    long topHandle = topHandle();
    long parentHandle = parent.parentingHandle();
    if (OS.GTK3) {
      OS.swt_fixed_move(parentHandle, topHandle, x, y);
    } else {
      boolean reset = OS.gtk_widget_get_visible(parentHandle);
      gtk_widget_set_visible(parentHandle, false);
      OS.gtk_fixed_move(parentHandle, topHandle, x, y);
      gtk_widget_set_visible(parentHandle, reset);
    }
  }
}
