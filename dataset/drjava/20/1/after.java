class PlaceHold {
  public void newFileCreated() {
    _definitionsPane.setDocument(_model.getDefinitionsDocument());
    _saveButton.setEnabled(false);
    _compileButton.setEnabled(false);
    _saveMenuItem.setEnabled(false);
    _compileMenuItem.setEnabled(false);
    _fileNameField.setText("Untitled");
    installNewDocumentListener(((DefinitionsDocument) (_model.getDefinitionsDocument())));
    _definitionsPane.grabFocus();
    _definitionsPane.getHighlighter().removeAllHighlights();
  }
}
