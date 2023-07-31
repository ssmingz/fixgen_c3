class PlaceHold {
  public int getTextHeight() {
    checkWidget();
    return OS.GTK_WIDGET_REQUISITION_HEIGHT(handle);
  }
}
