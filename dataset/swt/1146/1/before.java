class PlaceHold {
  Rectangle computeTrim(Theme theme, GC gc) {
    long notebookHandle = theme.notebookHandle;
    long gtkStyle = OS.gtk_widget_get_style(notebookHandle);
    int hborder;
    int vborder;
    if (OS.GTK_VERSION >= OS.VERSION(2, 4, 0)) {
      hborder = theme.getWidgetProperty(notebookHandle, "tab-hborder");
      vborder = theme.getWidgetProperty(notebookHandle, "tab-vborder");
    } else {
      hborder = 2;
      vborder = 2;
    }
    int focus_width = theme.getWidgetProperty(notebookHandle, "focus-line-width");
    int xthickness = OS.gtk_style_get_xthickness(gtkStyle);
    int ythickness = OS.gtk_style_get_ythickness(gtkStyle);
    int borderX = ((xthickness + TAB_CURVATURE) + focus_width) + hborder;
    int borderY = ((ythickness + TAB_CURVATURE) + focus_width) + vborder;
    int x = clientArea.x - borderX;
    int y = clientArea.y - borderY;
    int width = clientArea.width + (2 * borderX);
    int height = clientArea.height + (2 * borderY);
    return new Rectangle(x, y, width, height);
  }
}
