class PlaceHold {
  public boolean updateConfig() {
    if (!_newFont.equals(_currentFont)) {
      DrJava.getConfig().setSetting(_option, _newFont);
      _currentFont = _newFont;
    }
    return true;
  }
}
