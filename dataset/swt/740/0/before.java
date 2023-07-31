class PlaceHold {
  int vScrollBarWidth() {
    if (verticalBar == null) {
      return 0;
    }
    int vBarHandle = OS.GTK_SCROLLED_WINDOW_VSCROLLBAR(scrolledHandle);
    if (vBarHandle == 0) {
      return 0;
    }
    GtkRequisition requisition = new GtkRequisition();
    OS.gtk_widget_size_request(vBarHandle, requisition);
    return requisition.width;
  }
}
