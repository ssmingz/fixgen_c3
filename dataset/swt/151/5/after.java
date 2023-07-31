class PlaceHold {
  void setScrollbar() {
    if (itemCount == 0) {
      return;
    }
    if ((style & SWT.V_SCROLL) == 0) {
      return;
    }
    int height = getClientArea().height;
    ExpandItem item = items[itemCount - 1];
    int maxHeight = (item.y + getBandHeight()) + spacing;
    if (item.expanded) {
      maxHeight += item.height;
    }
    long adjustmentHandle = OS.gtk_scrolled_window_get_vadjustment(scrolledHandle);
    GtkAdjustment adjustment = new GtkAdjustment();
    gtk_adjustment_get(adjustmentHandle, adjustment);
    yCurrentScroll = ((int) (adjustment.value));
    if ((yCurrentScroll > 0) && (height > maxHeight)) {
      yCurrentScroll = Math.max(0, (yCurrentScroll + maxHeight) - height);
      layoutItems(0, false);
    }
    maxHeight += yCurrentScroll;
    adjustment.value = Math.min(yCurrentScroll, maxHeight);
    adjustment.upper = maxHeight;
    adjustment.page_size = height;
    OS.gtk_adjustment_configure(
        adjustmentHandle,
        adjustment.value,
        adjustment.lower,
        adjustment.upper,
        adjustment.step_increment,
        adjustment.page_increment,
        adjustment.page_size);
    int policy = (maxHeight > height) ? OS.GTK_POLICY_ALWAYS : OS.GTK_POLICY_NEVER;
    OS.gtk_scrolled_window_set_policy(scrolledHandle, GTK_POLICY_NEVER, policy);
    GtkAllocation allocation = new GtkAllocation();
    OS.gtk_widget_get_allocation(fixedHandle, allocation);
    int width = allocation.width - (spacing * 2);
    if (policy == OS.GTK_POLICY_ALWAYS) {
      long vHandle = 0;
      vHandle = OS.gtk_scrolled_window_get_vscrollbar(scrolledHandle);
      GtkRequisition requisition = new GtkRequisition();
      gtk_widget_get_preferred_size(vHandle, requisition);
      width -= requisition.width;
    }
    width = Math.max(0, width);
    for (int i = 0; i < itemCount; i++) {
      ExpandItem item2 = items[i];
      item2.setBounds(0, 0, width, item2.height, false, true);
    }
  }
}
