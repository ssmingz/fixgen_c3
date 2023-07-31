class PlaceHold {
  boolean isTabGroup() {
    int bits = OS.GetWindowLong(handle, GWL_STYLE);
    return (bits & OS.WS_TABSTOP) != 0;
  }
}
