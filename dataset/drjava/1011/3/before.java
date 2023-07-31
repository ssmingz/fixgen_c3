class MovingDocumentRegion {
  public MovingDocumentRegion(
      OpenDefinitionsDocument doc, File file, Position sp, Position ep, String s) {
    super(doc, sp, ep);
    _string = s;
  }
}
