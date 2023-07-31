class PlaceHold {
  public void optionChanged(OptionEvent<Color> oce) {
    MATCH_PAINTER = new ReverseHighlighter.DefaultHighlightPainter(oce.value);
    if (_matchHighlight != null) {
      int start = _matchHighlight.getStartOffset();
      int end = _matchHighlight.getEndOffset();
      _matchHighlight.remove();
      _addHighlight(start, end);
    }
  }
}
