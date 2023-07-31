class PlaceHold {
  private void _setKeyStroke(KeyStroke ks) {
    _newKey = ks;
    _keyField.setText(_option.format(_newKey));
  }
}
