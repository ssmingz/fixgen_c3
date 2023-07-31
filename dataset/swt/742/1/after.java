class PlaceHold {
  public int getTextHeight() {
    checkWidget();
    GtkRequisition requisition = new GtkRequisition();
    gtk_widget_size_request(handle, requisition);
    return OS.GTK_WIDGET_REQUISITION_HEIGHT(handle);
  }
}
