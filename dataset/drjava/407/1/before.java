class PlaceHold {
  public void scrollToDocumentAndOffset(
      final OpenDefinitionsDocument doc,
      final int offset,
      final boolean shouldHighlight,
      final boolean shouldAddToHistory) {
    boolean toSameDoc = _model.getActiveDocument().equals(doc);
    if (!toSameDoc) {
      _model.setActiveDocument(doc);
      _findReplace.updateFirstDocInSearch();
    } else {
      _model.refreshActiveDocument();
    }
    Runnable command =
        new Runnable() {
          public void run() {
            int lineNumber = doc.getLineOfOffset(offset) + 1;
            if ((_currentDefPane.getSize().getWidth() > 0)
                && (_currentDefPane.getSize().getHeight() > 0)) {
              _currentDefPane.centerViewOnOffset(offset);
              _currentDefPane.requestFocusInWindow();
            }
            if (shouldHighlight) {
              _removeThreadLocationHighlight();
              doc.acquireReadLock();
              try {
                int startOffset = doc._getOffset(lineNumber);
                if (startOffset > (-1)) {
                  int endOffset = doc._getLineEndPos(startOffset);
                  if (endOffset > (-1)) {
                    _currentThreadLocationHighlight =
                        _currentDefPane
                            .getHighlightManager()
                            .addHighlight(startOffset, endOffset, THREAD_PAINTER);
                  }
                }
              } finally {
                doc.releaseReadLock();
              }
            }
            if (_showDebugger) {
              _interactionsPane.requestFocusInWindow();
              _updateDebugStatus();
            }
          }
        };
    EventQueue.invokeLater(command);
  }
}
