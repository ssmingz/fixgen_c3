class PlaceHold {
  public boolean getEditable() {
    checkWidget();
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    return (bits & OS.ES_READONLY) == 0;
  }
}
