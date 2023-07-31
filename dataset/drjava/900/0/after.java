class PlaceHold {
  public void removeErrorHighlight() {
    if (_errorHighlightTag != null) {
      if (CodeStatus.DEVELOPMENT) {
        _highlightManager.removeHighlight(((HighlightManager.HighlightInfo) (_errorHighlightTag)));
      } else {
        getHighlighter().removeHighlight(_errorHighlightTag);
      }
      _errorHighlightTag = null;
    }
  }
}
