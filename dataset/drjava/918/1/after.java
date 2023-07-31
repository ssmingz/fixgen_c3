class PlaceHold {
  public int getLength() {
    final DefinitionsDocument doc = _doc;
    if (doc == null) {
      return _rec.getText().length();
    }
    return doc.getLength();
  }
}
