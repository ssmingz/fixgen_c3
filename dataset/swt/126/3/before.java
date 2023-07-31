class PlaceHold {
  int getResizeMode(double x, double y) {
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(shellHandle, allocation);
    int width = allocation.width;
    int height = allocation.height;
    int border = OS.gtk_container_get_border_width(shellHandle);
    int mode = 0;
    if (y >= (height - border)) {
      mode = OS.GDK_BOTTOM_SIDE;
      if (x >= ((width - border) - 16)) {
        mode = OS.GDK_BOTTOM_RIGHT_CORNER;
      } else if (x <= (border + 16)) {
        mode = OS.GDK_BOTTOM_LEFT_CORNER;
      }
    } else if (x >= (width - border)) {
      mode = OS.GDK_RIGHT_SIDE;
      if (y >= ((height - border) - 16)) {
        mode = OS.GDK_BOTTOM_RIGHT_CORNER;
      } else if (y <= (border + 16)) {
        mode = OS.GDK_TOP_RIGHT_CORNER;
      }
    } else if (y <= border) {
      mode = OS.GDK_TOP_SIDE;
      if (x <= (border + 16)) {
        mode = OS.GDK_TOP_LEFT_CORNER;
      } else if (x >= ((width - border) - 16)) {
        mode = OS.GDK_TOP_RIGHT_CORNER;
      }
    } else if (x <= border) {
      mode = OS.GDK_LEFT_SIDE;
      if (y <= (border + 16)) {
        mode = OS.GDK_TOP_LEFT_CORNER;
      } else if (y >= ((height - border) - 16)) {
        mode = OS.GDK_BOTTOM_LEFT_CORNER;
      }
    }
    return mode;
  }
}
