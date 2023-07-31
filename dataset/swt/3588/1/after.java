class PlaceHold {
  public void setVisible(boolean visible) {
    checkWidget();
    if ((style & (SWT.BAR | SWT.DROP_DOWN)) != 0) {
      return;
    }
    if (visible) {
      sendEvent(Show);
      if (getItemCount() != 0) {
        int parentHandle = parent.fixedHandle;
        int width = OS.GTK_WIDGET_WIDTH(parentHandle);
        int height = OS.GTK_WIDGET_HEIGHT(parentHandle);
        int barHandle = OS.gtk_menu_bar_new();
        OS.gtk_container_add(parentHandle, barHandle);
        OS.gtk_fixed_move(parentHandle, barHandle, width, height);
        OS.gtk_widget_show(barHandle);
        int itemHandle = OS.gtk_image_menu_item_new_with_label(new byte[1]);
        OS.gtk_menu_shell_insert(barHandle, itemHandle, 0);
        OS.gtk_widget_show(itemHandle);
        OS.gtk_menu_shell_select_item(barHandle, itemHandle);
        Callback GtkMenuBarEventFunc = new Callback(this, "GtkMenuBarEventFunc", 3);
        OS.gtk_signal_connect(barHandle, event_after, GtkMenuBarEventFunc.getAddress(), 0);
        OS.gtk_signal_connect(
            barHandle, button_press_event, GtkMenuBarEventFunc.getAddress(), MouseDown);
        int address = 0;
        Callback GtkMenuPositionFunc = null;
        if (hasLocation) {
          GtkMenuPositionFunc = new Callback(this, "GtkMenuPositionFunc", 5);
          address = GtkMenuPositionFunc.getAddress();
        }
        OS.gtk_menu_popup(
            handle, barHandle, itemHandle, address, 0, 0, OS.gtk_get_current_event_time());
        if (GtkMenuPositionFunc != null) {
          GtkMenuPositionFunc.dispose();
        }
        Display display = getDisplay();
        while ((!isDisposed()) && getVisible()) {
          if (!display.readAndDispatch()) {
            display.sleep();
          }
        }
        OS.gdk_pointer_ungrab(GDK_CURRENT_TIME);
        OS.gtk_widget_destroy(barHandle);
        GtkMenuBarEventFunc.dispose();
      } else {
        sendEvent(Hide);
      }
    } else {
      OS.gtk_menu_popdown(handle);
    }
  }
}
