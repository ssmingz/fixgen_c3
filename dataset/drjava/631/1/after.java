class PlaceHold {
  private int _getFirstNonWSCharPos(int pos) throws BadLocationException {
    _doc.acquireReadLock();
    try {
      return _doc._getFirstNonWSCharPos(pos);
    } finally {
      _doc.releaseReadLock();
    }
  }
}
