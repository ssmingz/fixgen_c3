class PlaceHold {
  private boolean _saveProjectAs() {
    _saveChooser.removeChoosableFileFilter(_projectFilter);
    _saveChooser.removeChoosableFileFilter(_javaSourceFilter);
    _saveChooser.removeChoosableFileFilter(_unfilteredJavaSourceFilter);
    _saveChooser.setFileFilter(_projectFilter);
    if (_currentProjFile != FileOps.NULL_FILE) {
      _saveChooser.setSelectedFile(_currentProjFile);
    }
    _saveChooser.setMultiSelectionEnabled(false);
    int rc = _saveChooser.showSaveDialog(this);
    if (rc == JFileChooser.APPROVE_OPTION) {
      File file = _saveChooser.getSelectedFile();
      if ((file != null) && ((!file.exists()) || MainFrameUtils.verifyOverwrite(this, file))) {
        _model.setProjectFile(file);
        _currentProjFile = file;
      }
    }
    return rc == JFileChooser.APPROVE_OPTION;
  }
}
