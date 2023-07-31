class PlaceHold {
  public int _getLineEndPos(int pos) {
    DefinitionsDocument doc = getDocument();
    doc.acquireReadLock();
    try {
      return doc._getLineEndPos(pos);
    } finally {
      doc.releaseReadLock();
    }
  }
}
