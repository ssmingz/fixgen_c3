class PlaceHold {
  public String getText() {
    DefinitionsDocument doc = getDocument();
    doc.readLock();
    try {
      return doc.getText(0, doc.getLength());
    } catch (BadLocationException e) {
      throw new UnexpectedException(e);
    } finally {
      readUnlock();
    }
  }
}
