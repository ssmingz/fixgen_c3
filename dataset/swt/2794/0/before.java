class PlaceHold {
  @Override
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    int xborder = 0;
    int yborder = 0;
    Rectangle trim = super.computeTrim(x, y, width, height);
    if (OS.GTK3) {
      GtkBorder tmp = new GtkBorder();
      long context = OS.gtk_widget_get_style_context(handle);
      OS.gtk_style_context_get_padding(context, GTK_STATE_FLAG_NORMAL, tmp);
      if ((style & SWT.BORDER) != 0) {
        OS.gtk_style_context_get_border(context, GTK_STATE_FLAG_NORMAL, tmp);
        trim.x -= tmp.left;
        trim.y -= tmp.top;
        trim.width += tmp.left + tmp.right;
        trim.height += tmp.top + tmp.bottom;
      }
    } else {
      Point thickness = getThickness(handle);
      if ((this.style & SWT.BORDER) != 0) {
        xborder += thickness.x;
        yborder += thickness.y;
      }
      long fontDesc = getFontDescription();
      int fontSize = OS.pango_font_description_get_size(fontDesc);
      int arrowSize = Math.max(OS.PANGO_PIXELS(fontSize), MIN_ARROW_WIDTH);
      arrowSize = arrowSize - (arrowSize % 2);
      trim.width += arrowSize + (2 * thickness.x);
    }
    int[] property = new int[1];
    OS.gtk_widget_style_get(handle, interior_focus, property, 0);
    if (property[0] == 0) {
      OS.gtk_widget_style_get(handle, focus_line_width, property, 0);
      xborder += property[0];
      yborder += property[0];
    }
    trim.x -= xborder;
    trim.y -= yborder;
    trim.width += 2 * xborder;
    trim.height += 2 * yborder;
    GtkBorder innerBorder = Display.getEntryInnerBorder(handle);
    trim.x -= innerBorder.left;
    trim.y -= innerBorder.top;
    trim.width += innerBorder.left + innerBorder.right;
    trim.height += innerBorder.top + innerBorder.bottom;
    return new Rectangle(trim.x, trim.y, trim.width, trim.height);
  }
}
