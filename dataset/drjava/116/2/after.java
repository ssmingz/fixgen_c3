class PlaceHold {
  public int commentLines(int selStart, int selEnd) {
    int toReturn = selEnd;
    if (selStart == selEnd) {
      writeLock();
      try {
        synchronized (_reduced) {
          setCurrentLocation(selStart);
          Position oldCurrentPosition = createPosition(_currentLocation);
          _commentLine();
          toReturn += WING_COMMENT_OFFSET;
        }
      } catch (BadLocationException e) {
        throw new UnexpectedException(e);
      } finally {
        writeUnlock();
      }
    } else {
      toReturn = _commentBlock(selStart, selEnd);
    }
    _undoManager.endLastCompoundEdit();
    return toReturn;
  }
}
