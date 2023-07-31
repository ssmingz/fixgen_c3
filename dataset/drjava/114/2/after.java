class PlaceHold {
  public void activeDocumentChanged(OpenDefinitionsDocument active) {
    _switchDefScrollPane();
    boolean isModified = active.isModifiedSinceSave();
    boolean canCompile = (!isModified) && (!active.isUntitled());
    _saveAction.setEnabled(isModified);
    _compileAction.setEnabled(canCompile);
    _errorPanel.getErrorListPane().selectNothing();
    int pos = _currentDefPane.getCaretPosition();
    _currentDefPane.getErrorCaretListener().updateHighlight(pos);
    _setCurrentDirectory(active);
    updateFileTitle();
    _currentDefPane.grabFocus();
  }
}
