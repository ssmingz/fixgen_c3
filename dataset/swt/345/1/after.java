class PlaceHold {
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    Rectangle trim = super.computeTrim(x, y, width, height);
    int xborder = 0;
    int yborder = 0;
    if ((style & SWT.SINGLE) != 0) {
      if ((style & SWT.BORDER) != 0) {
        long style = OS.gtk_widget_get_style(handle);
        xborder += OS.gtk_style_get_xthickness(style);
        yborder += OS.gtk_style_get_ythickness(style);
      }
      GtkBorder innerBorder = Display.getEntryInnerBorder(handle);
      trim.x -= innerBorder.left;
      trim.y -= innerBorder.top;
      trim.width += innerBorder.left + innerBorder.right;
      trim.height += innerBorder.top + innerBorder.bottom;
    } else {
      int borderWidth = OS.gtk_container_get_border_width(handle);
      xborder += borderWidth;
      yborder += borderWidth;
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
    trim.width += SPACE_FOR_CURSOR;
    return new Rectangle(trim.x, trim.y, trim.width, trim.height);
  }
}
