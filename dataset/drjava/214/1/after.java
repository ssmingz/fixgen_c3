class PlaceHold {
  public File getOpenFile() throws OperationCanceledException {
    _openChooser.setSelectedFile(null);
    int rc = _openChooser.showOpenDialog(this);
    return getChosenFile(_openChooser, rc);
  }
}
