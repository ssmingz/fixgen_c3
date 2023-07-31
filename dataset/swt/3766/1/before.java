class PlaceHold {
  void getSize(int[] width, int[] height) {
    if ((data.width != (-1)) && (data.height != (-1))) {
      width[0] = data.width;
      height[0] = data.height;
      return;
    }
    if (data.drawable != 0) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 24, 0)) {
        width[0] = OS.gdk_window_get_width(data.drawable);
        height[0] = OS.gdk_window_get_height(data.drawable);
      } else {
        OS.gdk_drawable_get_size(data.drawable, width, height);
      }
      return;
    }
    if (OS.USE_CAIRO) {
      int surface = Cairo.cairo_get_target(handle);
      switch (Cairo.cairo_surface_get_type(surface)) {
        case Cairo.CAIRO_SURFACE_TYPE_IMAGE:
          width[0] = Cairo.cairo_image_surface_get_width(surface);
          height[0] = Cairo.cairo_image_surface_get_height(surface);
          break;
        case Cairo.CAIRO_SURFACE_TYPE_XLIB:
          width[0] = Cairo.cairo_xlib_surface_get_width(surface);
          height[0] = Cairo.cairo_xlib_surface_get_height(surface);
          break;
      }
    }
  }
}
