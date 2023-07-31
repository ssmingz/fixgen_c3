class PlaceHold {
  public void setTopIndex(int index) {
    checkWidget();
    if ((style & SWT.SINGLE) != 0) {
      return;
    }
    if (index > getLineCount()) {
      return;
    }
    int adjustmentHandle = OS.gtk_scrolled_window_get_vadjustment(scrolledHandle);
    GtkAdjustment adjustment = new GtkAdjustment(adjustmentHandle);
    int adjust = ((int) ((index * adjustment.upper) / getLineCount()));
    if (adjust <= 0) {
      adjust = 0;
      verticalBar.setSelection(0);
    } else {
      verticalBar.setSelection(adjust);
    }
    OS.gtk_adjustment_value_changed(handle);
    int topindex = getTopIndex();
    int lineheight = getLineHeight();
    while (topindex != index) {
      adjust = adjust + lineheight;
      verticalBar.setSelection(adjust + lineheight);
      OS.gtk_adjustment_value_changed(handle);
      topindex = getTopIndex();
    }
  }
}
