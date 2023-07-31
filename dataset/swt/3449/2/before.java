class PlaceHold {
  void gtk_render_check(
      long style,
      long window,
      int state_type,
      int shadow_type,
      long widget,
      byte[] detail,
      int x,
      int y,
      int width,
      int height) {
    if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
      long cairo = OS.gdk_cairo_create(window);
      long context = OS.gtk_widget_get_style_context(style);
      OS.gtk_render_check(context, cairo, context, y, width, height);
      Cairo.cairo_destroy(cairo);
    } else {
      OS.gtk_paint_check(
          style, window, state_type, shadow_type, null, widget, detail, x, y, width, height);
    }
  }
}
