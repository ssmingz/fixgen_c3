class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    if (visible == OS.GTK_WIDGET_MAPPED(handle)) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (getItemCount() != 0) {
        int address = 0;
        Callback GtkMenuPositionFunc = null;
        if (hasLocation) {
          GtkMenuPositionFunc = new Callback(this, "GtkMenuPositionFunc", 5);
          address = GtkMenuPositionFunc.getAddress();
        }
        OS.gtk_menu_popup(handle, 0, 0, address, 0, 0, OS.gtk_get_current_event_time());
        if (GtkMenuPositionFunc != null) {
          GtkMenuPositionFunc.dispose();
        }
      } else {
        sendEvent(Hide);
      }
    } else {
      OS.gtk_menu_popdown(handle);
    }
  }
}
