class PlaceHold {
  public void removeErrorHighlight() {
    if (_errorHighlightTag != null) {
      getHighlighter().removeHighlight(_errorHighlightTag);
      _errorHighlightTag = null;
    }
  }
}
