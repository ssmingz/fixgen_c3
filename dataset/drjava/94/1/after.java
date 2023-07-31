class PlaceHold {
  protected void interpretIgnoreResult(String input) throws EditDocumentException {
    EditDocumentInterface interactionsDoc = _model.getInteractionsDocument();
    interactionsDoc.insertText(interactionsDoc.getLength(), input, DEFAULT_STYLE);
    _model.interpretCurrentInteraction();
  }
}
