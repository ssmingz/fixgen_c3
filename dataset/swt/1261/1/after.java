class PlaceHold {
  public boolean isSelected(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      return false;
    }
    return OS.IsDataBrowserItemSelected(handle, index + 1);
  }
}
