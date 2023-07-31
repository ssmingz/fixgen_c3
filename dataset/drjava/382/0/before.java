class PlaceHold {
  public void fileSaved(OpenDefinitionsDocument doc) {
    _saveAction.setEnabled(false);
    updateFileTitle();
    _currentDefPane.requestFocus();
  }
}
