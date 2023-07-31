class PlaceHold {
  int hScrollBarWidth() {
    if (horizontalBar == null) {
      return 0;
    }
    int hBarHandle = OS.GTK_SCROLLED_WINDOW_HSCROLLBAR(scrolledHandle);
    if (hBarHandle == 0) {
      return 0;
    }
    GtkRequisition requisition = new GtkRequisition();
    OS.gtk_widget_size_request(hBarHandle, requisition);
    return requisition.height;
  }
}
