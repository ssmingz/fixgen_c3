class PlaceHold {
  public Rectangle getThumbTrackBounds() {
    checkWidget();
    int x = 0;
    int y = 0;
    int width;
    int height;
    int[] has_stepper = new int[1];
    OS.gtk_widget_style_get(handle, has_backward_stepper, has_stepper, 0);
    boolean hasA = has_stepper[0] != 0;
    OS.gtk_widget_style_get(handle, has_secondary_backward_stepper, has_stepper, 0);
    boolean hasB = has_stepper[0] != 0;
    OS.gtk_widget_style_get(handle, has_forward_stepper, has_stepper, 0);
    boolean hasC = has_stepper[0] != 0;
    OS.gtk_widget_style_get(handle, has_secondary_forward_stepper, has_stepper, 0);
    boolean hasD = has_stepper[0] != 0;
    GtkAllocation allocation = new GtkAllocation();
    gtk_widget_get_allocation(handle, allocation);
    if ((style & SWT.VERTICAL) != 0) {
      int stepperSize = allocation.width;
      x = allocation.x;
      if (hasA) {
        y += stepperSize;
      }
      if (hasB) {
        y += stepperSize;
      }
      width = allocation.width;
      height = allocation.height - y;
      if (hasC) {
        height -= stepperSize;
      }
      if (hasD) {
        height -= stepperSize;
      }
      if (height < 0) {
        int[] slider_start = new int[1];
        int[] slider_end = new int[1];
        gtk_range_get_slider_range(handle, slider_start, slider_end);
        y = slider_start[0];
        height = 0;
      }
    } else {
      int stepperSize = allocation.height;
      if (hasA) {
        x += stepperSize;
      }
      if (hasB) {
        x += stepperSize;
      }
      y = allocation.y;
      width = allocation.width - x;
      if (hasC) {
        width -= stepperSize;
      }
      if (hasD) {
        width -= stepperSize;
      }
      height = allocation.height;
      if (width < 0) {
        int[] slider_start = new int[1];
        int[] slider_end = new int[1];
        gtk_range_get_slider_range(handle, slider_start, slider_end);
        x = slider_start[0];
        width = 0;
      }
    }
    Rectangle rect = new Rectangle(x, y, width, height);
    int[] origin_x = new int[1];
    int[] origin_y = new int[1];
    long window = gtk_widget_get_window(parent.scrolledHandle);
    if (window != 0) {
      OS.gdk_window_get_origin(window, origin_x, origin_y);
    }
    rect.x += origin_x[0];
    rect.y += origin_y[0];
    window = gtk_widget_get_window(parent.handle);
    if (window != 0) {
      OS.gdk_window_get_origin(window, origin_x, origin_y);
    }
    rect.x -= origin_x[0];
    rect.y -= origin_y[0];
    return rect;
  }
}
