class PlaceHold {
  public void fileSaved(OpenDefinitionsDocument doc) {
    _saveAction.setEnabled(false);
    _revertAction.setEnabled(true);
    updateFileTitle();
    _currentDefPane.requestFocus();
  }
}
