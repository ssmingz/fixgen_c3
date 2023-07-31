class PlaceHold {
  public void toggleBookmark() {
    final OpenDefinitionsDocument doc = _model.getActiveDocument();
    int startSel = _currentDefPane.getSelectionStart();
    int endSel = _currentDefPane.getSelectionEnd();
    doc.readLock();
    try {
      if (startSel > endSel) {
        int temp = startSel;
        startSel = endSel;
        endSel = temp;
      } else if (startSel == endSel) {
        endSel = doc.getLineEndPos(startSel);
        startSel = doc.getLineStartPos(startSel);
      }
      DocumentRegion r = _model.getBookmarkManager().getRegionOverlapping(doc, startSel, endSel);
      if (r == null) {
        final Position startPos = doc.createPosition(startSel);
        final Position endPos = doc.createPosition(endSel);
        _model
            .getBookmarkManager()
            .addRegion(
                new DocumentRegion() {
                  public OpenDefinitionsDocument getDocument() {
                    return doc;
                  }

                  public File getFile() throws FileMovedException {
                    return doc.getFile();
                  }

                  public int getStartOffset() {
                    return startPos.getOffset();
                  }

                  public int getEndOffset() {
                    return endPos.getOffset();
                  }
                });
      } else {
        _model.getBookmarkManager().removeRegion(r);
      }
    } catch (BadLocationException ble) {
      throw new UnexpectedException(ble);
    } finally {
      doc.readUnlock();
    }
  }
}
