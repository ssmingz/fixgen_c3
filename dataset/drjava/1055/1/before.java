class PlaceHold {
  private void _saveAll() {
    try {
      if (_model.isProjectActive()) {
        _saveProject();
      }
      _model.saveAllFiles(_saveSelector);
    } catch (IOException ioe) {
      _showIOError(ioe);
    }
  }
}
