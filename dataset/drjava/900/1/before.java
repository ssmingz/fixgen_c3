class PlaceHold {
  private void _removePreviousHighlight() {
    if (_matchHighlight != null) {
      getHighlighter().removeHighlight(_matchHighlight);
      _matchHighlight = null;
    }
  }
}
