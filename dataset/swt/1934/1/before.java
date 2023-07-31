class PlaceHold {
  void drawText(Theme theme, String text, int flags, GC gc, Rectangle bounds) {
    long widget = getTextHandle(theme);
    long gtkStyle = OS.gtk_widget_get_style(widget);
    long drawable = gc.getGCData().drawable;
    theme.transferClipping(gc, gtkStyle);
    byte[] buffer = Converter.wcsToMbcs(null, text, true);
    long layout = OS.gtk_widget_create_pango_layout(widget, buffer);
    int[] width = new int[1];
    int[] height = new int[1];
    OS.pango_layout_get_size(layout, width, height);
    OS.pango_layout_set_width(layout, bounds.width * OS.PANGO_SCALE);
    int x = bounds.x;
    int y = bounds.y;
    if ((flags & DrawData.DRAW_LEFT) != 0) {
      OS.pango_layout_set_alignment(layout, PANGO_ALIGN_LEFT);
    }
    if ((flags & DrawData.DRAW_HCENTER) != 0) {
      OS.pango_layout_set_alignment(layout, PANGO_ALIGN_CENTER);
    }
    if ((flags & DrawData.DRAW_RIGHT) != 0) {
      OS.pango_layout_set_alignment(layout, PANGO_ALIGN_RIGHT);
    }
    if ((flags & DrawData.DRAW_VCENTER) != 0) {
      y += (bounds.height - OS.PANGO_PIXELS(height[0])) / 2;
    }
    if ((flags & DrawData.DRAW_BOTTOM) != 0) {
      y += bounds.height - OS.PANGO_PIXELS(height[0]);
    }
    int state_type = getStateType(DrawData.WIDGET_WHOLE);
    byte[] detail = Converter.wcsToMbcs(null, "label", true);
    gtk_render_layout(gtkStyle, drawable, state_type, false, null, widget, detail, x, y, layout);
    OS.g_object_unref(layout);
  }
}
