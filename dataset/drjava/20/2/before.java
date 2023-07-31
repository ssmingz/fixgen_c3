class PlaceHold {
  public void fileOpened(File file) {
    _definitionsPane.setDocument(_model.getDefinitionsDocument());
    _saveButton.setEnabled(false);
    _compileButton.setEnabled(true);
    _fileNameField.setText(file.getName());
    installNewDocumentListener(((DefinitionsDocument) (_model.getDefinitionsDocument())));
    _definitionsPane.grabFocus();
  }
}
