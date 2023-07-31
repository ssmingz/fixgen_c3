class PlaceHold {
  public void updateListPane() {
    try {
      int numErrors = _errorsWithoutPositions.length + _errors.length;
      _errorListPositions = new Position[numErrors];
      if ((_errors.length == 0) && (_errorsWithoutPositions.length == 0)) {
        _updateNoErrors();
      } else {
        _updateWithErrors();
      }
    } catch (BadLocationException impossible) {
    }
    revalidate();
  }
}
