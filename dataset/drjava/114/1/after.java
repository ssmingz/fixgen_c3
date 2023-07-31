class PlaceHold {
  public void compileStarted() {
    _tabbedPane.setSelectedIndex(COMPILE_TAB);
    _saveAction.setEnabled(false);
    _compileAction.setEnabled(false);
    hourglassOn();
  }
}
