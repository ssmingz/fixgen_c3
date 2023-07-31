class PlaceHold {
  void moveHandle(int x, int y) {
    int topHandle = topHandle();
    int parentHandle = parent.parentingHandle();
    boolean visible = gtk_widget_get_visible(parentHandle);
    gtk_widget_set_visible(parentHandle, false);
    OS.gtk_fixed_move(parentHandle, topHandle, x, y);
    if (visible) {
      gtk_widget_set_visible(parentHandle, true);
    }
  }
}
