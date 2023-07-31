class PlaceHold {
  @Override
  public Rectangle computeTrim(int x, int y, int width, int height) {
    checkWidget();
    Rectangle trim = super.computeTrim(x, y, width, height);
    int xborder = 0;
    int yborder = 0;
    if ((style & SWT.SINGLE) != 0) {
      if (OS.GTK3) {
        GtkBorder tmp = new GtkBorder();
        long context = OS.gtk_widget_get_style_context(handle);
        OS.gtk_style_context_get_padding(context, GTK_STATE_FLAG_NORMAL, tmp);
        trim.x -= tmp.left;
        trim.y -= tmp.top;
        trim.width += tmp.left + tmp.right;
        trim.height += tmp.top + tmp.bottom;
        if ((style & SWT.BORDER) != 0) {
          OS.gtk_style_context_get_border(context, GTK_STATE_FLAG_NORMAL, tmp);
          trim.x -= tmp.left;
          trim.y -= tmp.top;
          trim.width += tmp.left + tmp.right;
          trim.height += tmp.top + tmp.bottom;
        }
        GdkRectangle icon_area = new GdkRectangle();
        OS.gtk_entry_get_icon_area(handle, GTK_ENTRY_ICON_PRIMARY, icon_area);
        trim.x -= icon_area.width;
        trim.width += icon_area.width;
        OS.gtk_entry_get_icon_area(handle, GTK_ENTRY_ICON_SECONDARY, icon_area);
        trim.width += icon_area.width;
      } else {
        if ((style & SWT.BORDER) != 0) {
          Point thickness = getThickness(handle);
          xborder += thickness.x;
          yborder += thickness.y;
        }
        GtkBorder innerBorder = Display.getEntryInnerBorder(handle);
        trim.x -= innerBorder.left;
        trim.y -= innerBorder.top;
        trim.width += innerBorder.left + innerBorder.right;
        trim.height += innerBorder.top + innerBorder.bottom;
      }
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
