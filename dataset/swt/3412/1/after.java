class PlaceHold {
  void drawImage(Theme theme, Image image, GC gc, Rectangle bounds) {
    int drawable = gc.getGCData().drawable;
    Rectangle rect = image.getBounds();
    int state_type = getStateType(DrawData.WIDGET_WHOLE);
    if (state_type == OS.GTK_STATE_NORMAL) {
      gc.drawImage(
          image, 0, 0, rect.width, rect.height, bounds.x, bounds.y, bounds.width, bounds.height);
    } else {
      int pixbuf = ImageList.createPixbuf(image);
      int source = OS.gtk_icon_source_new();
      if (source != 0) {
        OS.gtk_icon_source_set_pixbuf(source, pixbuf);
        int buttonHandle = theme.buttonHandle;
        int gtkStyle = OS.gtk_widget_get_style(buttonHandle);
        theme.transferClipping(gc, gtkStyle);
        int rendered =
            OS.gtk_style_render_icon(
                gtkStyle, source, GTK_TEXT_DIR_NONE, state_type, -1, buttonHandle, null);
        OS.g_object_unref(pixbuf);
        if (rendered != 0) {
          OS.gdk_draw_pixbuf(
              drawable,
              gc.handle,
              rendered,
              0,
              0,
              bounds.x,
              bounds.y,
              bounds.width,
              bounds.height,
              GDK_RGB_DITHER_NORMAL,
              0,
              0);
          OS.g_object_unref(rendered);
        }
        OS.gtk_icon_source_free(source);
      }
    }
  }
}
