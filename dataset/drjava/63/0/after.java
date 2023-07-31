class PlaceHold {
  public void setValue(KeyStroke value) {
    _newKey = value;
    _keyField.setText(_option.format(value));
  }
}
