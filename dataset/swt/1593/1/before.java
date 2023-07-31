class PlaceHold {
  public boolean isFocusControl() {
    checkWidget();
    if (((text.isFocusControl() || arrow.isFocusControl()) || list.isFocusControl())
        || popup.isFocusControl()) {
      return true;
    }
    return super.isFocusControl();
  }
}
