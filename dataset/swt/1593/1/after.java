class PlaceHold {
  public boolean isFocusControl() {
    checkWidget();
    if (((text.isFocusControl() || arrow.isFocusControl()) || list.isFocusControl())
        || getPopup().isFocusControl()) {
      return true;
    }
    return super.isFocusControl();
  }
}
