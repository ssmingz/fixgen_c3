class PlaceHold {
  public void setWidth(int width) {
    checkWidget();
    if ((style & SWT.SEPARATOR) == 0) {
      return;
    }
    if (width < 0) {
      return;
    }
    OS.gtk_widget_set_size_request(handle, width, -1);
    parent.layoutItems();
  }
}
