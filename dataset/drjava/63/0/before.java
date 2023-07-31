class PlaceHold {
  public void setValue(KeyStroke value) {
    _newKey = value;
    _button.setText(_option.format(value));
  }
}
