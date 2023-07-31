class PlaceHold {
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
    _lastMouseClick = e;
    if ((viewToModel(e.getPoint()) < getSelectionStart())
        || (viewToModel(e.getPoint()) > getSelectionEnd())) {
      setCaretPosition(viewToModel(e.getPoint()));
    }
    if (_toggleBreakpointMenuItem != null) {
      _toggleBreakpointMenuItem.setEnabled(_mainFrame.inDebugMode());
    }
  }
}
