class PlaceHold {
  public boolean getVisible() {
    checkWidget();
    return OS.GTK_WIDGET_VISIBLE(handle);
  }
}
