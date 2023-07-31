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
      Cairo.cairo_current_font_extents(cairo, extents);
      double baseline = y + extents.ascent;
      Cairo.cairo_move_to(cairo, x, baseline);
      byte[] buffer = Converter.wcsToMbcs(null, string, true);
      Cairo.cairo_text_path(cairo, buffer);
      Cairo.cairo_fill(cairo);
      return;
    }
    setText(string, flags);
    int xmMnemonic = data.xmMnemonic;
    if (xmMnemonic != 0) {
      OS.XmStringDrawUnderline(
          data.display,
          data.drawable,
          data.renderTable,
          data.xmText,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null,
          xmMnemonic);
    } else if ((flags & SWT.DRAW_TRANSPARENT) != 0) {
      OS.XmStringDraw(
          data.display,
          data.drawable,
          data.renderTable,
          data.xmText,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null);
    } else {
      OS.XmStringDrawImage(
          data.display,
          data.drawable,
          data.renderTable,
          data.xmText,
          handle,
          x,
          y,
          0x7fffffff,
          XmALIGNMENT_BEGINNING,
          0,
          null);
    }
  }
}
