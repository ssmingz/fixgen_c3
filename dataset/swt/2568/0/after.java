class PlaceHold {
  int gtk_expose_event(int widget, int event) {
    if ((state & OBSCURED) != 0) {
      return 0;
    }
    int result = super.gtk_expose_event(widget, event);
    if (((style & SWT.SINGLE) != 0) && (message.length() > 0)) {
      int str = OS.gtk_entry_get_text(handle);
      if ((!gtk_widget_has_focus(handle)) && (OS.strlen(str) == 0)) {
        GdkEventExpose gdkEvent = new GdkEventExpose();
        OS.memmove(gdkEvent, event, sizeof);
        int window = paintWindow();
        int[] w = new int[1];
        int[] h = new int[1];
        gdk_window_get_size(window, w, h);
        GtkBorder innerBorder = Display.getEntryInnerBorder(handle);
        int width = (w[0] - innerBorder.left) - innerBorder.right;
        int height = (h[0] - innerBorder.top) - innerBorder.bottom;
        int context = OS.gtk_widget_get_pango_context(handle);
        int lang = OS.pango_context_get_language(context);
        int metrics = OS.pango_context_get_metrics(context, getFontDescription(), lang);
        int ascent = OS.PANGO_PIXELS(OS.pango_font_metrics_get_ascent(metrics));
        int descent = OS.PANGO_PIXELS(OS.pango_font_metrics_get_descent(metrics));
        OS.pango_font_metrics_unref(metrics);
        byte[] buffer = Converter.wcsToMbcs(null, message, true);
        int layout = OS.gtk_widget_create_pango_layout(handle, buffer);
        int line = OS.pango_layout_get_line(layout, 0);
        PangoRectangle rect = new PangoRectangle();
        OS.pango_layout_line_get_extents(line, null, rect);
        rect.y = OS.PANGO_PIXELS(rect.y);
        rect.height = OS.PANGO_PIXELS(rect.height);
        rect.width = OS.PANGO_PIXELS(rect.width);
        int y = ((((height - ascent) - descent) / 2) + ascent) + rect.y;
        if (rect.height > height) {
          y = (height - rect.height) / 2;
        } else if (y < 0) {
          y = 0;
        } else if ((y + rect.height) > height) {
          y = height - rect.height;
        }
        y += innerBorder.top;
        int x = innerBorder.left;
        boolean rtl = (style & SWT.RIGHT_TO_LEFT) != 0;
        int alignment = style & ((SWT.LEFT | SWT.CENTER) | SWT.RIGHT);
        switch (alignment) {
          case SWT.LEFT:
            x = (rtl) ? width - rect.width : innerBorder.left;
            break;
          case SWT.CENTER:
            x = (width - rect.width) / 2;
            break;
          case SWT.RIGHT:
            x = (rtl) ? innerBorder.left : width - rect.width;
            break;
        }
        int style = OS.gtk_widget_get_style(handle);
        GdkColor textColor = new GdkColor();
        OS.gtk_style_get_text(style, GTK_STATE_INSENSITIVE, textColor);
        GdkColor baseColor = new GdkColor();
        OS.gtk_style_get_base(style, GTK_STATE_NORMAL, baseColor);
        if (OS.USE_CAIRO) {
          int cairo = OS.gdk_cairo_create(window);
          Cairo.cairo_set_source_rgba(
              cairo,
              (textColor.red & 0xffff) / ((float) (0xffff)),
              (textColor.green & 0xffff) / ((float) (0xffff)),
              (textColor.blue & 0xffff) / ((float) (0xffff)),
              1);
          Cairo.cairo_move_to(cairo, x, y);
          OS.pango_cairo_show_layout(cairo, layout);
          Cairo.cairo_destroy(cairo);
        } else {
          int gc = OS.gdk_gc_new(window);
          OS.gdk_draw_layout_with_colors(window, gc, x, y, layout, textColor, baseColor);
          OS.g_object_unref(gc);
        }
        OS.g_object_unref(layout);
      }
    }
    return result;
  }
}
