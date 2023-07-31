class PlaceHold {
  @Override
  public Rectangle computeTrim(int x, int y, int width, int height) {
    if (isCalendar()) {
      return super.computeTrim(x, y, width, height);
    }
    checkWidget();
    Rectangle trim = super.computeTrim(x, y, width, height);
    int xborder = 0;
    int yborder = 0;
    if (OS.GTK3) {
      GtkBorder tmp = new GtkBorder();
      long context = OS.gtk_widget_get_style_context(textEntryHandle);
      int styleState = OS.gtk_widget_get_state_flags(textEntryHandle);
      OS.gtk_style_context_get_padding(context, styleState, tmp);
      trim.x -= tmp.left;
      trim.y -= tmp.top;
      trim.width += tmp.left + tmp.right;
      trim.height += tmp.top + tmp.bottom;
      if ((style & SWT.BORDER) != 0) {
        OS.gtk_style_context_get_border(context, styleState, tmp);
        trim.x -= tmp.left;
        trim.y -= tmp.top;
        trim.width += tmp.left + tmp.right;
        trim.height += tmp.top + tmp.bottom;
      }
    } else {
      if ((style & SWT.BORDER) != 0) {
        Point thickness = getThickness(textEntryHandle);
        xborder += thickness.x;
        yborder += thickness.y;
      }
      GtkBorder innerBorder = Display.getEntryInnerBorder(textEntryHandle);
      trim.x -= innerBorder.left;
      trim.y -= innerBorder.top;
      trim.width += innerBorder.left + innerBorder.right;
      trim.height += innerBorder.top + innerBorder.bottom;
    }
    trim.x -= xborder;
    trim.y -= yborder;
    trim.width += 2 * xborder;
    trim.height += 2 * yborder;
    trim.width += SPACE_FOR_CURSOR;
    return new Rectangle(trim.x, trim.y, trim.width, trim.height);
  }
}
