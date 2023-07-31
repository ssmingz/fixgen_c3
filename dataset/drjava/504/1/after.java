class PlaceHold {
  public int _getLineStartPos(int pos) {
    DefinitionsDocument doc = getDocument();
    doc.acquireReadLock();
    try {
      return doc._getLineStartPos(pos);
    } finally {
      doc.releaseReadLock();
    }
  }
}
