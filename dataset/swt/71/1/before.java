class PlaceHold {
  @Override
  long windowProc(long handle, long arg0, long user_data) {
    switch (((int) (user_data))) {
      case EXPOSE_EVENT_INVERSE:
        {
          if ((state & OBSCURED) != 0) {
            break;
          }
          if (OS.USE_CAIRO) {
            Control control = findBackgroundControl();
            boolean draw = (control != null) && (control.backgroundImage != null);
            if ((OS.GTK3 && (!draw)) && ((state & CANVAS) != 0)) {
              GdkRGBA rgba = new GdkRGBA();
              long context = OS.gtk_widget_get_style_context(handle);
              OS.gtk_style_context_get_background_color(context, GTK_STATE_FLAG_NORMAL, rgba);
              draw = rgba.alpha == 0;
            }
            if (draw) {
              if (OS.GTK3) {
                long cairo = arg0;
                GdkRectangle rect = new GdkRectangle();
                OS.gdk_cairo_get_clip_rectangle(cairo, rect);
                if (control == null) {
                  control = this;
                }
                long window = OS.gtk_widget_get_window(handle);
                if (window != 0) {
                  drawBackground(control, window, 0, 0, rect.x, rect.y, rect.width, rect.height);
                } else {
                  drawBackground(control, 0, cairo, 0, rect.x, rect.y, rect.width, rect.height);
                }
              } else {
                GdkEventExpose gdkEvent = new GdkEventExpose();
                OS.memmove(gdkEvent, arg0, sizeof);
                long paintWindow = paintWindow();
                long window = gdkEvent.window;
                if (window != paintWindow) {
                  break;
                }
                drawBackground(
                    control,
                    window,
                    gdkEvent.region,
                    gdkEvent.area_x,
                    gdkEvent.area_y,
                    gdkEvent.area_width,
                    gdkEvent.area_height);
              }
            }
          }
          break;
        }
    }
    return super.windowProc(handle, arg0, user_data);
  }
}
