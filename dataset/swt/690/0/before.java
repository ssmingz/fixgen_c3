class PlaceHold {
  public void draw(
      GC gc,
      int x,
      int y,
      int selectionStart,
      int selectionEnd,
      Color selectionForeground,
      Color selectionBackground) {
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
    int length = text.length();
    if (length == 0) {
      return;
    }
    boolean hasSelection =
        ((selectionStart <= selectionEnd) && (selectionStart != (-1))) && (selectionEnd != (-1));
    if (!hasSelection) {
      OS.gdk_draw_layout(gc.data.drawable, gc.handle, x, y, layout);
    } else {
      selectionStart = Math.min(Math.max(0, selectionStart), length - 1);
      selectionEnd = Math.min(Math.max(0, selectionEnd), length - 1);
      length = ((int) (OS.g_utf8_strlen(OS.pango_layout_get_text(layout), -1)));
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
        OS.gdk_draw_layout_with_colors(
            gc.data.drawable,
            gc.handle,
            x,
            y,
            layout,
            selectionForeground.handle,
            selectionBackground.handle);
      } else {
        int ptr = OS.pango_layout_get_text(layout);
        Region clipping = new Region();
        gc.getClipping(clipping);
        int byteSelStart = ((int) (OS.g_utf8_offset_to_pointer(ptr, selectionStart) - ptr));
        int byteSelEnd = ((int) (OS.g_utf8_offset_to_pointer(ptr, selectionEnd + 1) - ptr));
        int strlen = OS.strlen(ptr);
        byteSelStart = Math.min(byteSelStart, strlen);
        byteSelEnd = Math.min(byteSelEnd, strlen);
        OS.gdk_draw_layout(gc.data.drawable, gc.handle, x, y, layout);
        int[] ranges = new int[] {byteSelStart, byteSelEnd};
        int rgn = OS.gdk_pango_layout_get_clip_region(layout, x, y, ranges, ranges.length / 2);
        if (rgn != 0) {
          OS.gdk_gc_set_clip_region(gc.handle, rgn);
          OS.gdk_region_destroy(rgn);
        }
        OS.gdk_draw_layout_with_colors(
            gc.data.drawable,
            gc.handle,
            x,
            y,
            layout,
            selectionForeground.handle,
            selectionBackground.handle);
        gc.setClipping(clipping);
      }
    }
  }
}
