class PlaceHold {
  void moveHandle(int x, int y) {
    int topHandle = topHandle();
    int parentHandle = parent.parentingHandle();
    boolean visible = gtk_widget_get_visible(parentHandle);
    OS.GTK_WIDGET_UNSET_FLAGS(parentHandle, GTK_VISIBLE);
    OS.gtk_fixed_move(parentHandle, topHandle, x, y);
    if (visible) {
      gtk_widget_set_visible(parentHandle, true);
    }
  }
}
