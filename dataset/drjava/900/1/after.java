class PlaceHold {
  private void _removePreviousHighlight() {
    if (_matchHighlight != null) {
      if (CodeStatus.DEVELOPMENT) {
        _highlightManager.removeHighlight(((HighlightManager.HighlightInfo) (_matchHighlight)));
      } else {
        getHighlighter().removeHighlight(_matchHighlight);
      }
      _matchHighlight = null;
    }
  }
}
