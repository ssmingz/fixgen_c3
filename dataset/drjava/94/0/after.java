class PlaceHold {
  protected String getInteractionsText() throws EditDocumentException {
    EditDocumentInterface doc = _model.getInteractionsDocument();
    return doc.getDocText(0, doc.getLength());
  }
}
