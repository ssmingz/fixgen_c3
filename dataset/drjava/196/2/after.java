class PlaceHold {
  private void reset() {
    final File bd = _mainFrame.getModel().getBuildDirectory();
    final JTextField bdTextField = _builtDirSelector.getFileField();
    if (bd == null) {
      bdTextField.setText("");
    } else {
      _builtDirSelector.setFileField(bd);
    }
    final File wd = _mainFrame.getModel().getWorkingDirectory();
    final JTextField wdTextField = _workDirSelector.getFileField();
    if (wd == null) {
      wdTextField.setText("");
    } else {
      _workDirSelector.setFileField(wd);
    }
    final File mc = _mainFrame.getModel().getMainClass();
    final JTextField mcTextField = _jarMainClassSelector.getFileField();
    if (mc == null) {
      mcTextField.setText("");
    } else {
      _jarMainClassSelector.setFileField(mc);
    }
    ClassPathVector cp = _mainFrame.getModel().getExtraClassPath();
    _extraClassPathList.setValue(cp.asFileVector());
  }
}
