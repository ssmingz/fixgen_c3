class PlaceHold {
  public void addToBrowserHistory() {
    if (!EventQueue.isDispatchThread()) {
      return;
    }
    final OpenDefinitionsDocument doc = getActiveDocument();
    int startPos = 0;
    int endPos = 0;
    File file = null;
    if (doc != null) {
      try {
        startPos = doc.createPosition(doc.getCaretPosition()).getOffset();
        endPos = doc.createPosition(doc.getLineEndPos(doc.getCaretPosition())).getOffset();
        file = doc.getFile();
      } catch (FileMovedException fme) {
      } catch (BadLocationException ble) {
        throw new UnexpectedException(ble);
      }
      getBrowserHistoryManager().addRegion(new SimpleDocumentRegion(doc, file, startPos, endPos));
    }
  }
}
