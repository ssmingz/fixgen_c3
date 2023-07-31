class PlaceHold {
  public void fileSaved(OpenDefinitionsDocument doc) {
    _saveAction.setEnabled(false);
    _compileAction.setEnabled(true);
    updateFileTitle();
    _currentDefPane.grabFocus();
  }
}
