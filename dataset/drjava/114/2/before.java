class PlaceHold {
  public void activeDocumentChanged(OpenDefinitionsDocument active) {
    _switchDefScrollPane();
    boolean isModified = active.isModifiedSinceSave();
    boolean canCompile = (!isModified) && (!active.isUntitled());
    _saveButton.setEnabled(isModified);
    _compileButton.setEnabled(canCompile);
    _saveMenuItem.setEnabled(isModified);
    _compileMenuItem.setEnabled(canCompile);
    _errorPanel.getErrorListPane().selectNothing();
    int pos = _currentDefPane.getCaretPosition();
    _currentDefPane.getErrorCaretListener().updateHighlight(pos);
    _setCurrentDirectory(active);
    updateFileTitle();
    _currentDefPane.grabFocus();
  }
}
