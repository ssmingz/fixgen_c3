class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int topHandle = topHandle();
    if (OS.GTK_WIDGET_VISIBLE(topHandle) == visible) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      OS.gtk_widget_show(topHandle);
    } else {
      OS.gtk_widget_hide(topHandle);
      OS.gtk_widget_unrealize(topHandle);
      sendEvent(Hide);
    }
  }
}
