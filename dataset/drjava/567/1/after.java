class PlaceHold {
  public void mouseReleased(MouseEvent e) {
    _error = ((JUnitError) (_errorAtPoint(e.getPoint())));
    if (_isEmptySelection() && (_error != null)) {
      _errorListPane.switchToError(_error);
    } else {
      selectNothing();
    }
    super.mouseReleased(e);
  }
}
