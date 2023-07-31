class PlaceHold {
  public boolean isSelected(int index) {
    checkWidget();
    return OS.IsDataBrowserItemSelected(handle, index + 1);
  }
}
