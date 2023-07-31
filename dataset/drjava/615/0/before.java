class PlaceHold {
  public void compileStarted() {
    _tabbedPane.setSelectedIndex(COMPILE_TAB);
    _saveButton.setEnabled(false);
    _compileButton.setEnabled(false);
    _saveMenuItem.setEnabled(false);
    _compileMenuItem.setEnabled(false);
    hourglassOn();
  }
}
