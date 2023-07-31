class PlaceHold {
  public void setVisible(boolean vis) {
    assert EventQueue.isDispatchThread();
    validate();
    if (vis) {
      updateList(0);
      _mainFrame.hourglassOn();
      _mainFrame.installModalWindowAdapter(this, NO_OP, OK);
      toFront();
    } else {
      _mainFrame.removeModalWindowAdapter(this);
      _mainFrame.hourglassOff();
      _mainFrame.toFront();
    }
    super.setVisible(vis);
  }
}
