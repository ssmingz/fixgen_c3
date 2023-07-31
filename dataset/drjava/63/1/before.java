class PlaceHold {
  private void _setKeyStroke(KeyStroke ks) {
    _newKey = ks;
    _button.setText(_option.format(_newKey));
  }
}
