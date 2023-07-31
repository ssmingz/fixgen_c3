class PlaceHold {
  public void setRedraw(boolean redraw) {
    checkWidget();
    if (redraw) {
      if ((--drawCount) == 0) {
        if (redrawWindow != 0) {
          long window = paintWindow();
          OS.gdk_window_hide(redrawWindow);
          OS.gdk_window_destroy(redrawWindow);
          OS.gdk_window_set_events(window, OS.gtk_widget_get_events(paintHandle()));
          redrawWindow = 0;
        }
      }
    } else if ((drawCount++) == 0) {
      if (gtk_widget_get_realized(handle)) {
        long window = paintWindow();
        Rectangle rect = getBounds();
        GdkWindowAttr attributes = new GdkWindowAttr();
        attributes.width = rect.width;
        attributes.height = rect.height;
        attributes.event_mask = OS.GDK_EXPOSURE_MASK;
        attributes.window_type = OS.GDK_WINDOW_CHILD;
        redrawWindow = OS.gdk_window_new(window, attributes, 0);
        if (redrawWindow != 0) {
          int mouseMask =
              ((((((((OS.GDK_BUTTON_PRESS_MASK | OS.GDK_BUTTON_RELEASE_MASK)
                                              | OS.GDK_ENTER_NOTIFY_MASK)
                                          | OS.GDK_LEAVE_NOTIFY_MASK)
                                      | OS.GDK_POINTER_MOTION_MASK)
                                  | OS.GDK_POINTER_MOTION_HINT_MASK)
                              | OS.GDK_BUTTON_MOTION_MASK)
                          | OS.GDK_BUTTON1_MOTION_MASK)
                      | OS.GDK_BUTTON2_MOTION_MASK)
                  | OS.GDK_BUTTON3_MOTION_MASK;
          OS.gdk_window_set_events(window, OS.gdk_window_get_events(window) & (~mouseMask));
          if (OS.GTK3) {
            OS.gdk_window_set_background_pattern(redrawWindow, 0);
          } else {
            OS.gdk_window_set_back_pixmap(redrawWindow, 0, false);
          }
          OS.gdk_window_show(redrawWindow);
        }
      }
    }
  }
}
