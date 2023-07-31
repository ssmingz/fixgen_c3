class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if (visible) {
      int grabHandle = OS.gtk_grab_get_current();
      if (grabHandle != 0) {
        OS.gtk_grab_remove(grabHandle);
        OS.gdk_pointer_ungrab(GDK_CURRENT_TIME);
      }
      sendEvent(Show);
      if (isDisposed()) {
        return;
      }
      mapped = false;
      OS.gtk_widget_show(shellHandle);
      while ((!isDisposed()) && (!mapped)) {
        OS.gtk_main_iteration();
      }
      if (isDisposed()) {
        return;
      }
      adjustTrim();
    } else {
      OS.gtk_widget_hide(shellHandle);
      sendEvent(Hide);
    }
  }
}
