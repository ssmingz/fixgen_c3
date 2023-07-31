class PlaceHold {
  void resizeBounds(int width, int height, boolean notify) {
    if (redrawWindow != 0) {
      OS.gdk_window_resize(redrawWindow, width, height);
    }
    if (enableWindow != 0) {
      OS.gdk_window_resize(enableWindow, width, height);
    }
    int border = OS.gtk_container_get_border_width(shellHandle);
    int menuHeight = 0;
    if (menuBar != null) {
      int menuHandle = menuBar.handle;
      OS.gtk_widget_set_size_request(menuHandle, -1, -1);
      GtkRequisition requisition = new GtkRequisition();
      OS.gtk_widget_size_request(menuHandle, requisition);
      menuHeight = requisition.height;
      OS.gtk_widget_set_size_request(menuHandle, width - (border * 2), menuHeight);
      height = Math.max(1, height - menuHeight);
    }
    OS.gtk_fixed_move(fixedHandle, scrolledHandle, 0, menuHeight);
    OS.gtk_widget_set_size_request(scrolledHandle, width - (border * 2), height - (border * 2));
    OS.gtk_container_resize_children(fixedHandle);
    if (notify) {
      resized = true;
      sendEvent(Resize);
      if (isDisposed()) {
        return;
      }
      if (layout != null) {
        layout.layout(this, false);
      }
    }
  }
}
