class PlaceHold {
  public void draw(
      GC gc,
      int x,
      int y,
      int selectionStart,
      int selectionEnd,
      Color selectionForeground,
      Color selectionBackground,
      int flags) {
    checkLayout();
    computeRuns();
    if (gc == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    if (gc.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((selectionForeground != null) && selectionForeground.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    if ((selectionBackground != null) && selectionBackground.isDisposed()) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    gc.checkGC(FOREGROUND);
    int length = text.length();
    x += Math.min(indent, wrapIndent);
    boolean hasSelection =
        ((selectionStart <= selectionEnd) && (selectionStart != (-1))) && (selectionEnd != (-1));
    GCData data = gc.data;
    long cairo = data.cairo;
    if (((flags & (SWT.FULL_SELECTION | SWT.DELIMITER_SELECTION)) != 0)
        && (hasSelection || ((flags & SWT.LAST_LINE_SELECTION) != 0))) {
      long[] attrs = new long[1];
      int[] nAttrs = new int[1];
      PangoLogAttr logAttr = new PangoLogAttr();
      PangoRectangle rect = new PangoRectangle();
      int lineCount = OS.pango_layout_get_line_count(layout);
      long ptr = OS.pango_layout_get_text(layout);
      long iter = OS.pango_layout_get_iter(layout);
      if (selectionBackground == null) {
        selectionBackground = device.getSystemColor(COLOR_LIST_SELECTION);
      }
      if (cairo != 0) {
        Cairo.cairo_save(cairo);
        GdkColor color = selectionBackground.handle;
        Cairo.cairo_set_source_rgba(
            cairo,
            (color.red & 0xffff) / ((float) (0xffff)),
            (color.green & 0xffff) / ((float) (0xffff)),
            (color.blue & 0xffff) / ((float) (0xffff)),
            data.alpha / ((float) (0xff)));
      } else {
        OS.gdk_gc_set_foreground(gc.handle, selectionBackground.handle);
      }
      int lineIndex = 0;
      do {
        int lineEnd;
        OS.pango_layout_iter_get_line_extents(iter, null, rect);
        if (OS.pango_layout_iter_next_line(iter)) {
          int bytePos = OS.pango_layout_iter_get_index(iter);
          lineEnd = ((int) (OS.g_utf16_pointer_to_offset(ptr, ptr + bytePos)));
        } else {
          lineEnd = ((int) (OS.g_utf16_strlen(ptr, -1)));
        }
        boolean extent = false;
        if ((lineIndex == (lineCount - 1)) && ((flags & SWT.LAST_LINE_SELECTION) != 0)) {
          extent = true;
        } else {
          if (attrs[0] == 0) {
            OS.pango_layout_get_log_attrs(layout, attrs, nAttrs);
          }
          OS.memmove(logAttr, attrs[0] + (lineEnd * PangoLogAttr.sizeof), sizeof);
          if (!logAttr.is_line_break) {
            if ((selectionStart <= lineEnd) && (lineEnd <= selectionEnd)) {
              extent = true;
            }
          } else if (((selectionStart <= lineEnd) && (lineEnd < selectionEnd))
              && ((flags & SWT.FULL_SELECTION) != 0)) {
            extent = true;
          }
        }
        if (extent) {
          int lineX = (x + OS.PANGO_PIXELS(rect.x)) + OS.PANGO_PIXELS(rect.width);
          int lineY = y + OS.PANGO_PIXELS(rect.y);
          int height = OS.PANGO_PIXELS(rect.height);
          if ((ascent != (-1)) && (descent != (-1))) {
            height = Math.max(height, ascent + descent);
          }
          int width = ((flags & SWT.FULL_SELECTION) != 0) ? 0x7fff : height / 3;
          if (cairo != 0) {
            Cairo.cairo_rectangle(cairo, lineX, lineY, width, height);
            Cairo.cairo_fill(cairo);
          } else {
            OS.gdk_draw_rectangle(data.drawable, gc.handle, 1, lineX, lineY, width, height);
          }
        }
        lineIndex++;
      } while (lineIndex < lineCount);
      OS.pango_layout_iter_free(iter);
      if (attrs[0] != 0) {
        OS.g_free(attrs[0]);
      }
      if (cairo != 0) {
        Cairo.cairo_restore(cairo);
      } else {
        OS.gdk_gc_set_foreground(gc.handle, data.foreground);
      }
    }
    if (length == 0) {
      return;
    }
    if (!hasSelection) {
      if (cairo != 0) {
        if ((data.style & SWT.MIRRORED) != 0) {
          Cairo.cairo_save(cairo);
          Cairo.cairo_scale(cairo, -1, 1);
          Cairo.cairo_translate(cairo, ((-2) * x) - width(), 0);
        }
        Cairo.cairo_move_to(cairo, x, y);
        OS.pango_cairo_show_layout(cairo, layout);
        drawBorder(gc, x, y, null);
        if ((data.style & SWT.MIRRORED) != 0) {
          Cairo.cairo_restore(cairo);
        }
      } else {
        OS.gdk_draw_layout(data.drawable, gc.handle, x, y, layout);
        drawBorder(gc, x, y, null);
      }
    } else {
      selectionStart = Math.min(Math.max(0, selectionStart), length - 1);
      selectionEnd = Math.min(Math.max(0, selectionEnd), length - 1);
      length = ((int) (OS.g_utf16_strlen(OS.pango_layout_get_text(layout), -1)));
      selectionStart = translateOffset(selectionStart);
      selectionEnd = translateOffset(selectionEnd);
      if (selectionForeground == null) {
        selectionForeground = device.getSystemColor(COLOR_LIST_SELECTION_TEXT);
      }
      if (selectionBackground == null) {
        selectionBackground = device.getSystemColor(COLOR_LIST_SELECTION);
      }
      boolean fullSelection = (selectionStart == 0) && (selectionEnd == (length - 1));
      if (fullSelection) {
        if (cairo != 0) {
          long ptr = OS.pango_layout_get_text(layout);
          if ((data.style & SWT.MIRRORED) != 0) {
            Cairo.cairo_save(cairo);
            Cairo.cairo_scale(cairo, -1, 1);
            Cairo.cairo_translate(cairo, ((-2) * x) - width(), 0);
          }
          drawWithCairo(
              gc,
              x,
              y,
              0,
              OS.strlen(ptr),
              fullSelection,
              selectionForeground.handle,
              selectionBackground.handle);
          if ((data.style & SWT.MIRRORED) != 0) {
            Cairo.cairo_restore(cairo);
          }
        } else {
          OS.gdk_draw_layout_with_colors(
              data.drawable,
              gc.handle,
              x,
              y,
              layout,
              selectionForeground.handle,
              selectionBackground.handle);
          drawBorder(gc, x, y, selectionForeground.handle);
        }
      } else {
        long ptr = OS.pango_layout_get_text(layout);
        int byteSelStart = ((int) (OS.g_utf16_offset_to_pointer(ptr, selectionStart) - ptr));
        int byteSelEnd = ((int) (OS.g_utf16_offset_to_pointer(ptr, selectionEnd + 1) - ptr));
        int strlen = OS.strlen(ptr);
        byteSelStart = Math.min(byteSelStart, strlen);
        byteSelEnd = Math.min(byteSelEnd, strlen);
        if (cairo != 0) {
          if ((data.style & SWT.MIRRORED) != 0) {
            Cairo.cairo_save(cairo);
            Cairo.cairo_scale(cairo, -1, 1);
            Cairo.cairo_translate(cairo, ((-2) * x) - width(), 0);
          }
          drawWithCairo(
              gc,
              x,
              y,
              byteSelStart,
              byteSelEnd,
              fullSelection,
              selectionForeground.handle,
              selectionBackground.handle);
          if ((data.style & SWT.MIRRORED) != 0) {
            Cairo.cairo_restore(cairo);
          }
        } else {
          Region clipping = new Region();
          gc.getClipping(clipping);
          OS.gdk_draw_layout(data.drawable, gc.handle, x, y, layout);
          drawBorder(gc, x, y, null);
          int[] ranges = new int[] {byteSelStart, byteSelEnd};
          long rgn = OS.gdk_pango_layout_get_clip_region(layout, x, y, ranges, ranges.length / 2);
          if (rgn != 0) {
            OS.gdk_gc_set_clip_region(gc.handle, rgn);
            OS.gdk_region_destroy(rgn);
          }
          OS.gdk_draw_layout_with_colors(
              data.drawable,
              gc.handle,
              x,
              y,
              layout,
              selectionForeground.handle,
              selectionBackground.handle);
          drawBorder(gc, x, y, selectionForeground.handle);
          gc.setClipping(clipping);
          clipping.dispose();
        }
      }
    }
    if (cairo != 0) {
      Cairo.cairo_new_path(cairo);
    }
  }
}
