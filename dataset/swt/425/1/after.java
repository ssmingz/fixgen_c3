class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (string.equals(text)) {
      return;
    }
    super.setText(string);
    width = -1;
    parent.setScrollWidth(this);
    redraw();
  }
}
