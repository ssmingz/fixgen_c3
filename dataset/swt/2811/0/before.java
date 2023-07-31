class PlaceHold {
  public void setText(String string) {
    checkWidget();
    if (string.equals(getText())) {
      return;
    }
    super.setText(string);
    parent.layoutItems();
    parent.redraw();
  }
}
