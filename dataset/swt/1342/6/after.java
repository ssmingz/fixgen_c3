class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    long topHandle = topHandle();
    return gtk_widget_get_sensitive(topHandle);
  }
}
