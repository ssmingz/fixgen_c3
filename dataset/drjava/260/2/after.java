class PlaceHold {
  private void _doFind() {
    if (_findField.getText().length() > 0) {
      _model.addToBrowserHistory();
      _updateMachine();
      final String findWord = _findField.getText();
      _machine.setFindWord(findWord);
      _machine.setReplaceWord(_replaceField.getText());
      _frame.clearStatusMessage();
      final boolean searchAll = _machine.getSearchAllDocuments();
      FindResult fr = _machine.findNext();
      OpenDefinitionsDocument doc = fr.getDocument();
      OpenDefinitionsDocument matchDoc = _model.getODDForDocument(doc);
      OpenDefinitionsDocument openDoc = _defPane.getOpenDefDocument();
      final boolean docChanged = !matchDoc.equals(openDoc);
      final int pos = fr.getFoundOffset();
      if (searchAll) {
        if (docChanged) {
          _model.setActiveDocument(matchDoc);
        } else {
          _model.refreshActiveDocument();
        }
      }
      if (fr.getWrapped() && (!searchAll)) {
        Toolkit.getDefaultToolkit().beep();
        if (!_machine.isSearchBackwards()) {
          _frame.setStatusMessage("Search wrapped to beginning.");
        } else {
          _frame.setStatusMessage("Search wrapped to end.");
        }
      }
      if (fr.getAllDocsWrapped() && searchAll) {
        Toolkit.getDefaultToolkit().beep();
        _frame.setStatusMessage("Search wrapped around all documents.");
      }
      if (pos >= 0) {
        Caret c = _defPane.getCaret();
        c.setDot(c.getDot());
        _defPane.setCaretPosition(pos);
        _caretChanged = true;
        _updateMachine();
        final Runnable command =
            new Runnable() {
              public void run() {
                _selectFoundOrReplacedItem(findWord.length());
                _replaceAction.setEnabled(true);
                _replaceFindNextAction.setEnabled(true);
                _replaceFindPreviousAction.setEnabled(true);
                _machine.setLastFindWord();
              }
            };
        if (docChanged) {
          SwingUtilities.invokeLater(command);
        } else {
          command.run();
        }
      } else {
        Toolkit.getDefaultToolkit().beep();
        final StringBuilder statusMessage = new StringBuilder("Search text \"");
        if (findWord.length() <= 50) {
          statusMessage.append(findWord);
        } else {
          statusMessage.append(findWord.substring(0, 49) + "...");
        }
        statusMessage.append("\" not found.");
        _frame.setStatusMessage(statusMessage.toString());
      }
    }
    if (!DrJava.getConfig().getSetting(FIND_REPLACE_FOCUS_IN_DEFPANE).booleanValue()) {
      _findField.requestFocusInWindow();
    }
  }
}
