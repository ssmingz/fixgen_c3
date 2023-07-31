class PlaceHold {
  public String getText() {
    final DefinitionsDocument doc = _doc;
    if (doc != null) {
      return doc.getText();
    }
    return _rec.getText();
  }
}
