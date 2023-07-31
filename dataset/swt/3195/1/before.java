class PlaceHold {
  boolean drawCaret() {
    if (parent == null) {
      return false;
    }
    if (parent.isDisposed()) {
      return false;
    }
    int window = parent.paintWindow();
    int gc = OS.gdk_gc_new(window);
    int fontHandle = parent.fontHandle();
    int hStyle = OS.gtk_widget_get_style(fontHandle);
    GtkStyle style = new GtkStyle(hStyle);
    GdkColor color = new GdkColor();
    color.red = ((short) (style.fg0_red ^ style.bg0_red));
    color.green = ((short) (style.fg0_green ^ style.bg0_green));
    color.blue = ((short) (style.fg0_blue ^ style.bg0_blue));
    int colormap = OS.gdk_colormap_get_system();
    OS.gdk_colormap_alloc_color(colormap, color, true, true);
    OS.gdk_gc_set_foreground(gc, color);
    OS.gdk_gc_set_function(gc, GDK_XOR);
    int nWidth = width;
    int nHeight = height;
    if (image != null) {
      Rectangle rect = image.getBounds();
      nWidth = rect.width;
      nHeight = rect.height;
    }
    if (nWidth <= 0) {
      nWidth = 2;
    }
    OS.gdk_draw_rectangle(window, gc, 1, x, y, nWidth, nHeight);
    OS.g_object_unref(gc);
    OS.gdk_colormap_free_colors(colormap, color, 1);
    return true;
  }
}
