class PlaceHold {
  void draw(Theme theme, GC gc, Rectangle bounds) {
    long notebookHandle = theme.notebookHandle;
    long gtkStyle = OS.gtk_widget_get_style(notebookHandle);
    long drawable = gc.getGCData().drawable;
    theme.transferClipping(gc, gtkStyle);
    int x = bounds.x;
    int y = bounds.y;
    int width = bounds.width;
    int height = bounds.height;
    if ((state[DrawData.WIDGET_WHOLE] & DrawData.SELECTED) == 0) {
      if ((parent.style & SWT.BOTTOM) == 0) {
        y += TAB_CURVATURE;
      }
      height -= TAB_CURVATURE;
    }
    int gap_side = OS.GTK_POS_BOTTOM;
    if ((parent.style & SWT.BOTTOM) != 0) {
      gap_side = OS.GTK_POS_TOP;
    }
    int state_type = getStateType(WIDGET_WHOLE);
    byte[] detail = Converter.wcsToMbcs(null, "tab", true);
    gtk_render_extension(
        gtkStyle,
        drawable,
        state_type,
        GTK_SHADOW_OUT,
        null,
        notebookHandle,
        detail,
        x,
        y,
        width,
        height,
        gap_side);
    if (clientArea != null) {
      int hborder = theme.getWidgetProperty(notebookHandle, "tab-hborder");
      int vborder = theme.getWidgetProperty(notebookHandle, "tab-vborder");
      int focus_line_width = theme.getWidgetProperty(notebookHandle, "focus-line-width");
      int xthickness = OS.gtk_style_get_xthickness(gtkStyle);
      int ythickness = OS.gtk_style_get_ythickness(gtkStyle);
      int borderX = ((xthickness + TAB_CURVATURE) + focus_line_width) + hborder;
      int borderY = ((ythickness + TAB_CURVATURE) + focus_line_width) + vborder;
      clientArea.x = bounds.x + borderX;
      clientArea.y = bounds.y + borderY;
      clientArea.width = bounds.width - (2 * borderX);
      clientArea.height = bounds.height - (2 * borderY);
    }
  }
}
