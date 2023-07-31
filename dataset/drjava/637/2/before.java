class PlaceHold {
  private void _setUpPanes() {
    JScrollPane listScroll =
        new BorderlessScrollPane(
            _docList,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JScrollPane defScroll = ((JScrollPane) (_defScrollPanes.get(_model.getActiveDocument())));
    if (_model.getDebugger() != null) {
      try {
        _debugPanel = new DebugPanel(this);
        _debugPanel.setPreferredSize(_debugPanel.getMinimumSize());
      } catch (NoClassDefFoundError e) {
        _debugPanel = null;
      }
    } else {
      _debugPanel = null;
    }
    _docSplitPane =
        new BorderlessSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, listScroll, defScroll);
    _debugSplitPane = new BorderlessSplitPane(JSplitPane.VERTICAL_SPLIT, true);
    _debugSplitPane.setBottomComponent(_debugPanel);
    _mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, _docSplitPane, _tabbedPane);
    _mainSplit.setResizeWeight(1.0);
    _debugSplitPane.setResizeWeight(1.0);
    getContentPane().add(_mainSplit, BorderLayout.CENTER);
    _mainSplit.setDividerLocation((2 * getHeight()) / 3);
    _mainSplit.setOneTouchExpandable(true);
    _debugSplitPane.setOneTouchExpandable(true);
    _docSplitPane.setDividerLocation(DOC_LIST_WIDTH);
    _docSplitPane.setOneTouchExpandable(true);
  }
}
