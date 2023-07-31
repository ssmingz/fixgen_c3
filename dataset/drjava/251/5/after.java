class PlaceHold {
  private void _resetMainPane() {
    _linkError = false;
    _mainDocPane = new JEditorPane();
    _mainDocPane.setEditable(false);
    for (int i = 0; i < _hyperlinkListeners.size(); i++) {
      _mainDocPane.addHyperlinkListener(_hyperlinkListeners.get(i));
    }
    _displayPage(_lastURL);
    _splitPane.setRightComponent(new BorderlessScrollPane(_mainDocPane));
    _splitPane.setDividerLocation(LEFT_PANEL_WIDTH);
  }
}
