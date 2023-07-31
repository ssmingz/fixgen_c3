class PlaceHold {
  public void indentLines(int selStart, int selEnd) {
    DefinitionsDocument doc = getDocument();
    doc.acquireWriteLock();
    try {
      doc.indentLines(selStart, selEnd);
    } finally {
      doc.releaseWriteLock();
    }
  }
}
