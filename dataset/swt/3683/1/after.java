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
    GdkColor color = new GdkColor();
    color.red = ((short) (0xffff));
    color.green = ((short) (0xffff));
    color.blue = ((short) (0xffff));
    int colormap = OS.gdk_colormap_get_system();
    OS.gdk_colormap_alloc_color(colormap, color, true, true);
    OS.gdk_gc_set_foreground(gc, color);
    OS.gdk_gc_set_function(gc, GDK_XOR);
    if (((image != null) && (!image.isDisposed())) && (image.mask == 0)) {
      int[] width = new int[1];
      int[] height = new int[1];
      OS.gdk_drawable_get_size(image.pixmap, width, height);
      int nX = x;
      if ((parent.style & SWT.MIRRORED) != 0) {
        nX = (parent.getClientWidth() - width[0]) - nX;
      }
      OS.gdk_draw_drawable(window, gc, image.pixmap, 0, 0, nX, y, width[0], height[0]);
    } else {
      int nWidth = width;
      int nHeight = height;
      if (nWidth <= 0) {
        nWidth = DEFAULT_WIDTH;
      }
      int nX = x;
      if ((parent.style & SWT.MIRRORED) != 0) {
        nX = (parent.getClientWidth() - nWidth) - nX;
      }
      OS.gdk_draw_rectangle(window, gc, 1, nX, y, nWidth, nHeight);
    }
    OS.g_object_unref(gc);
    OS.gdk_colormap_free_colors(colormap, color, 1);
    return true;
  }
}
