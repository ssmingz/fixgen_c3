class PlaceHold {
  int setBounds(int x, int y, int width, int height, boolean move, boolean resize) {
    boolean wrap =
        ((labelHandle != 0) && ((style & SWT.WRAP) != 0)) && gtk_widget_get_visible(labelHandle);
    if (wrap) {
      OS.gtk_widget_set_size_request(boxHandle, -1, -1);
    }
    int result = super.setBounds(x, y, width, height, move, resize);
    if (wrap) {
      GtkAllocation allocation = new GtkAllocation();
      gtk_widget_get_allocation(boxHandle, allocation);
      int boxWidth = allocation.width;
      int boxHeight = allocation.height;
      int labelLayout = OS.gtk_label_get_layout(labelHandle);
      int pangoWidth = OS.pango_layout_get_width(labelLayout);
      OS.pango_layout_set_width(labelLayout, -1);
      int[] w = new int[1];
      int[] h = new int[1];
      OS.pango_layout_get_size(labelLayout, w, h);
      OS.pango_layout_set_width(labelLayout, pangoWidth);
      int imageWidth = 0;
      if (gtk_widget_get_visible(imageHandle)) {
        GtkRequisition requisition = new GtkRequisition();
        OS.gtk_widget_size_request(imageHandle, requisition);
        imageWidth = requisition.width;
        int[] spacing = new int[1];
        OS.g_object_get(boxHandle, OS.spacing, spacing, 0);
        imageWidth += spacing[0];
      }
      OS.gtk_widget_set_size_request(
          labelHandle, Math.min(OS.PANGO_PIXELS(w[0]), boxWidth - imageWidth), -1);
      GtkRequisition requisition = new GtkRequisition();
      OS.gtk_widget_size_request(boxHandle, requisition);
      allocation.width = boxWidth;
      allocation.height = boxHeight;
      OS.gtk_widget_size_allocate(boxHandle, allocation);
    }
    return result;
  }
}
