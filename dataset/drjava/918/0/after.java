class PlaceHold {
  public String getText() {
    final DefinitionsDocument doc = _doc;
    if (doc == null) {
      return _rec.getText();
    }
    return doc.getText();
  }
}
