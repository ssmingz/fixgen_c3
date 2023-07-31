class PlaceHold {
  public int getLength() {
    final DefinitionsDocument doc = _doc;
    if (doc != null) {
      return doc.getLength();
    }
    return _rec.getText().length();
  }
}
