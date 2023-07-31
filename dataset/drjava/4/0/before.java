class PlaceHold {
  public void setVisible(boolean vis) {
    assert EventQueue.isDispatchThread();
    validate();
    if (vis) {
      updateList(0);
      _mainFrame.hourglassOn();
      addWindowListener(_windowListener);
      _windowListenerActive = true;
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
