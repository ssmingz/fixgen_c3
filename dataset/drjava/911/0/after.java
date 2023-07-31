class PlaceHold {
  public String getText() {
    DefinitionsDocument doc = getDocument();
    doc.acquireReadLock();
    try {
      return doc.getText(0, doc.getLength());
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    } finally {
      releaseReadLock();
    }
  }
}
