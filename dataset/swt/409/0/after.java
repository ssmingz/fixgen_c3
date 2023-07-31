class PlaceHold {
  public void drawText(String string, int x, int y, int flags) {
    if (handle == 0) {
      SWT.error(ERROR_GRAPHIC_DISPOSED);
    }
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (string.length() == 0) {
      return;
    }
    int cairo = data.cairo;
    if (cairo != 0) {
      cairo_font_extents_t extents = new cairo_font_extents_t();
      Cairo.cairo_font_extents(cairo, extents);
      double baseline = y + extents.ascent;
      Cairo.cairo_move_to(cairo, x, baseline);
      byte[] buffer = Converter.wcsToMbcs(null, string, true);
      Cairo.cairo_text_path(cairo, buffer);
      Cairo.cairo_fill(cairo);
      return;
    }
    setString(string, flags);
    GdkColor background = null;
    GdkGCValues values = null;
    if ((flags & SWT.DRAW_TRANSPARENT) == 0) {
      values = new GdkGCValues();
      OS.gdk_gc_get_values(handle, values);
      background = new GdkColor();
      background.pixel = values.background_pixel;
      int colormap = OS.gdk_colormap_get_system();
      OS.gdk_colormap_query_color(colormap, background.pixel, background);
    }
    if (!data.xorMode) {
      OS.gdk_draw_layout_with_colors(data.drawable, handle, x, y, data.layout, null, background);
    } else {
      int layout = data.layout;
      int[] w = new int[1];
      int[] h = new int[1];
      OS.pango_layout_get_size(layout, w, h);
      int width = OS.PANGO_PIXELS(w[0]);
      int height = OS.PANGO_PIXELS(h[0]);
      int pixmap = OS.gdk_pixmap_new(OS.GDK_ROOT_PARENT(), width, height, -1);
      if (pixmap == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      int gdkGC = OS.gdk_gc_new(pixmap);
      if (gdkGC == 0) {
        SWT.error(ERROR_NO_HANDLES);
      }
      GdkColor foreground = new GdkColor();
      OS.gdk_gc_set_foreground(gdkGC, foreground);
      OS.gdk_draw_rectangle(pixmap, gdkGC, 1, 0, 0, width, height);
      if (values == null) {
        values = new GdkGCValues();
        OS.gdk_gc_get_values(handle, values);
      }
      foreground.pixel = values.foreground_pixel;
      OS.gdk_gc_set_foreground(gdkGC, foreground);
      OS.gdk_draw_layout_with_colors(pixmap, gdkGC, 0, 0, layout, null, background);
      OS.g_object_unref(gdkGC);
      OS.gdk_draw_drawable(data.drawable, handle, pixmap, 0, 0, x, y, width, height);
      OS.g_object_unref(pixmap);
    }
  }
}
