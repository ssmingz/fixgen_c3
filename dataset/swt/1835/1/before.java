class PlaceHold {
  int gtk_size_allocate(int widget, int allocation) {
    if ((image != null) && (image.mask != 0)) {
      if (OS.gdk_drawable_get_depth(mask) == 1) {
        int xoffset =
            ((int)
                (Math.floor(
                    (OS.GTK_WIDGET_X(widget)
                            + ((OS.GTK_WIDGET_WIDTH(widget)
                                    - OS.GTK_WIDGET_REQUISITION_WIDTH(widget))
                                * 0.5))
                        + 0.5)));
        int yoffset =
            ((int)
                (Math.floor(
                    (OS.GTK_WIDGET_Y(widget)
                            + ((OS.GTK_WIDGET_HEIGHT(widget)
                                    - OS.GTK_WIDGET_REQUISITION_HEIGHT(widget))
                                * 0.5))
                        + 0.5)));
        Rectangle b = image.getBounds();
        int gdkImagePtr = OS.gdk_drawable_get_image(mask, 0, 0, b.width, b.height);
        GdkImage gdkImage = new GdkImage();
        OS.memmove(gdkImage, gdkImagePtr);
        byte[] maskData = new byte[gdkImage.bpl * gdkImage.height];
        OS.memmove(maskData, gdkImage.mem, maskData.length);
        OS.g_object_unref(gdkImagePtr);
        Region region = new Region(display);
        for (int y = 0; y < b.height; y++) {
          for (int x = 0; x < b.width; x++) {
            int index = (y * gdkImage.bpl) + (x >> 3);
            int theByte = maskData[index] & 0xff;
            int mask = 1 << (x & 0x7);
            if ((theByte & mask) != 0) {
              region.add(xoffset + x, yoffset + y, 1, 1);
            }
          }
        }
        OS.gtk_widget_realize(handle);
        int window = OS.GTK_WIDGET_WINDOW(handle);
        OS.gdk_window_shape_combine_region(window, region.handle, 0, 0);
        region.dispose();
      }
    }
    return 0;
  }
}
