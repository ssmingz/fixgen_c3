class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    int topHandle = topHandle();
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
