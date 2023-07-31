class PlaceHold {
  int gtk_expose_event(int widget, int event) {
    if (widget == shellHandle) {
      if (isCustomResize()) {
        GdkEventExpose gdkEventExpose = new GdkEventExpose();
        OS.memmove(gdkEventExpose, event, sizeof);
        int style = OS.gtk_widget_get_style(widget);
        int window = gtk_widget_get_window(widget);
        int[] width = new int[1];
        int[] height = new int[1];
        gdk_window_get_size(window, width, height);
        GdkRectangle area = new GdkRectangle();
        area.x = gdkEventExpose.area_x;
        area.y = gdkEventExpose.area_y;
        area.width = gdkEventExpose.area_width;
        area.height = gdkEventExpose.area_height;
        byte[] detail = Converter.wcsToMbcs(null, "base", true);
        int border = OS.gtk_container_get_border_width(widget);
        int state = (display.activeShell == this) ? OS.GTK_STATE_SELECTED : OS.GTK_STATE_PRELIGHT;
        OS.gtk_paint_flat_box(
            style, window, state, GTK_SHADOW_NONE, area, widget, detail, 0, 0, width[0], border);
        OS.gtk_paint_flat_box(
            style,
            window,
            state,
            GTK_SHADOW_NONE,
            area,
            widget,
            detail,
            0,
            height[0] - border,
            width[0],
            border);
        OS.gtk_paint_flat_box(
            style,
            window,
            state,
            GTK_SHADOW_NONE,
            area,
            widget,
            detail,
            0,
            border,
            border,
            (height[0] - border) - border);
        OS.gtk_paint_flat_box(
            style,
            window,
            state,
            GTK_SHADOW_NONE,
            area,
            widget,
            detail,
            width[0] - border,
            border,
            border,
            (height[0] - border) - border);
        OS.gtk_paint_box(
            style, window, state, GTK_SHADOW_OUT, area, widget, detail, 0, 0, width[0], height[0]);
        return 1;
      }
      return 0;
    }
    return super.gtk_expose_event(widget, event);
  }
}
