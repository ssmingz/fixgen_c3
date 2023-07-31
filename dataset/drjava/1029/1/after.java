class PlaceHold {
  private void indentLines(int selStart, int selEnd) {
    _doc.acquireWriteLock();
    try {
      _doc.indentLines(selStart, selEnd);
    } finally {
      _doc.releaseWriteLock();
    }
  }
}
