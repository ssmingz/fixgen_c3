class PlaceHold {
  public void newFileCreated() {
    _definitionsPane.setDocument(_model.getDefinitionsDocument());
    _saveButton.setEnabled(false);
    _compileButton.setEnabled(false);
    _fileNameField.setText("Untitled");
    installNewDocumentListener(((DefinitionsDocument) (_model.getDefinitionsDocument())));
    _definitionsPane.grabFocus();
  }
}
