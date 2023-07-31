class PlaceHold {
  @Override
  long gtk_focus_in_event(long widget, long event) {
    long result = super.gtk_focus_in_event(widget, event);
    if (result != 0) {
      return result;
    }
    if (handle != 0) {
      GtkAllocation allocation = new GtkAllocation();
      OS.gtk_widget_get_allocation(handle, allocation);
      lastX = allocation.x;
      lastY = allocation.y;
    }
    return 0;
  }
}
