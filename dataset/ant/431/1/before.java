class PlaceHold {
  public void setValue(Object value) {
    Object old = _widget.getText();
    _widget.setText(String.valueOf(value));
  }
}
