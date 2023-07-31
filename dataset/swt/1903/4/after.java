class PlaceHold {
  void gtk_render_focus(
      long style,
      long window,
      int state_type,
      GdkRectangle area,
      long widget,
      byte[] detail,
      int x,
      int y,
      int width,
      int height) {
    if (OS.GTK3) {
      long cairo = OS.gdk_cairo_create(window);
      long context = OS.gtk_widget_get_style_context(style);
      OS.gtk_style_context_save(context);
      OS.gtk_style_context_set_state(context, OS.gtk_widget_get_state_flags(widget));
      Cairo.cairo_save(cairo);
      OS.gtk_render_focus(context, cairo, x, y, width, height);
      Cairo.cairo_restore(cairo);
      OS.gtk_style_context_restore(context);
      Cairo.cairo_destroy(cairo);
    } else {
      OS.gtk_paint_focus(style, window, state_type, area, widget, detail, x, y, width, height);
    }
  }
}
