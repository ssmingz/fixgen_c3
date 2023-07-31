class PlaceHold {
  public File getSaveFile() throws OperationCanceledException {
    _saveChooser.setSelectedFile(null);
    int rc = _saveChooser.showSaveDialog(this);
    return getChosenFile(_saveChooser, rc);
  }
}
