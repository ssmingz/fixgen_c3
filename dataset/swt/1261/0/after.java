class PlaceHold {
  public void setSelection(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      return;
    }
    setSelection(index, false);
  }
}
