class PlaceHold {
  public void fileSaved(OpenDefinitionsDocument doc) {
    _saveButton.setEnabled(false);
    _compileButton.setEnabled(true);
    _saveMenuItem.setEnabled(false);
    _compileMenuItem.setEnabled(true);
    updateFileTitle();
    _currentDefPane.grabFocus();
  }
}
