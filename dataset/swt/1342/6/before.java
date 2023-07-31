class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    int topHandle = topHandle();
    return gtk_widget_get_sensitive(topHandle);
  }
}
