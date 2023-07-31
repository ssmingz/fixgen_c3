class StaticDocumentRegion {
  public StaticDocumentRegion(OpenDefinitionsDocument doc, File file, int so, int eo, String s) {
    _doc = doc;
    _file = file;
    _startOffset = so;
    _endOffset = eo;
    _string = s;
  }
}
