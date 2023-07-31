class PlaceHold {
  public void addToBrowserHistory() {
    final OpenDefinitionsDocument doc = getActiveDocument();
    assert doc != null;
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            Position startPos = null;
            Position endPos = null;
            File file = FileOps.NULL_FILE;
            try {
              startPos = doc.createPosition(doc.getCaretPosition());
              endPos = doc.createPosition(doc.getLineEndPos(doc.getCaretPosition()));
              file = doc.getFile();
            } catch (FileMovedException fme) {
            } catch (BadLocationException ble) {
              throw new UnexpectedException(ble);
            }
            _browserHistoryManager.addBrowserRegion(
                new BrowserDocumentRegion(doc, startPos, endPos), _notifier);
          }
        });
  }
}
