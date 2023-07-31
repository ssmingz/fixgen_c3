class PlaceHold {
  public void setValue(Object value) {
    if (value == null) {
      value = "";
    }
    Object old = _widget.getText();
    _widget.setText(String.valueOf(value));
  }
}
