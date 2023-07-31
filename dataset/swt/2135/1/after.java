class PlaceHold {
  public Point computeSize(int wHint, int hHint, boolean changed) {
    checkWidget();
    if (((style & SWT.READ_ONLY) != 0) || (menuHandle != 0)) {
      return computeNativeSize(handle, wHint, hHint, changed);
    }
    if ((wHint != SWT.DEFAULT) && (wHint < 0)) {
      wHint = 0;
    }
    if ((hHint != SWT.DEFAULT) && (hHint < 0)) {
      hHint = 0;
    }
    int[] w = new int[1];
    int[] h = new int[1];
    OS.gtk_widget_realize(entryHandle);
    int layout = OS.gtk_entry_get_layout(entryHandle);
    OS.pango_layout_get_size(layout, w, h);
    int xborder = Display.INNER_BORDER;
    int yborder = Display.INNER_BORDER;
    int style = OS.gtk_widget_get_style(entryHandle);
    xborder += OS.gtk_style_get_xthickness(style);
    yborder += OS.gtk_style_get_ythickness(style);
    int[] property = new int[1];
    OS.gtk_widget_style_get(entryHandle, interior_focus, property, 0);
    if (property[0] == 0) {
      OS.gtk_widget_style_get(entryHandle, focus_line_width, property, 0);
      xborder += property[0];
      yborder += property[0];
    }
    int width = OS.PANGO_PIXELS(w[0]) + (xborder * 2);
    int height = OS.PANGO_PIXELS(h[0]) + (yborder * 2);
    GtkRequisition arrowRequesition = new GtkRequisition();
    OS.gtk_widget_size_request(buttonHandle, arrowRequesition);
    GtkRequisition listRequesition = new GtkRequisition();
    int listParent = OS.gtk_bin_get_child(popupHandle);
    OS.gtk_widget_size_request(listParent, listRequesition);
    width = Math.max(listRequesition.width, width) + arrowRequesition.width;
    width = (wHint == SWT.DEFAULT) ? width : wHint;
    height = (hHint == SWT.DEFAULT) ? height : hHint;
    return new Point(width, height);
  }
}
