class PlaceHold {
  public void setVisible(boolean vis) {
    assert EventQueue.isDispatchThread();
    validate();
    if (vis) {
      _mainFrame.hourglassOn();
      _mainFrame.installModalWindowAdapter(this, NO_OP, CANCEL);
      _documentListener.changedUpdate(null);
      _workDirDocumentListener.changedUpdate(null);
      _javaDocumentListener.changedUpdate(null);
      _javaWorkDirDocumentListener.changedUpdate(null);
      toFront();
    } else {
      _mainFrame.removeModalWindowAdapter(this);
      _mainFrame.hourglassOff();
      _mainFrame.toFront();
    }
    super.setVisible(vis);
  }
}
