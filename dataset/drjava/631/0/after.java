class PlaceHold {
  private int _getLineFirstCharPos(int pos) throws BadLocationException {
    _doc.acquireReadLock();
    try {
      return _doc._getLineFirstCharPos(pos);
    } finally {
      _doc.releaseReadLock();
    }
  }
}
