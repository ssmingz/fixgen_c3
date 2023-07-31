class PlaceHold {
  public void setVisible(boolean vis) {
    assert EventQueue.isDispatchThread();
    validate();
    if (vis) {
      _mainFrame.hourglassOn();
      addWindowListener(_windowListener);
      _windowListenerActive = true;
      _documentListener.changedUpdate(null);
      _workDirDocumentListener.changedUpdate(null);
      _javaDocumentListener.changedUpdate(null);
      _javaWorkDirDocumentListener.changedUpdate(null);
      toFront();
    } else {
      _windowListenerActive = false;
      removeWindowFocusListener(_windowListener);
      _mainFrame.hourglassOff();
      _mainFrame.toFront();
    }
    super.setVisible(vis);
  }
}
