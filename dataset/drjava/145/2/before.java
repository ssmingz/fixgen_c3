class PlaceHold {
  protected void assertReplThrewContinuationException(String code) throws DocumentAdapterException {
    assert _model instanceof IncompleteInputInteractionsModel;
    IncompleteInputInteractionsModel model = ((IncompleteInputInteractionsModel) (_model));
    InteractionsDocument doc = model.getDocument();
    doc.insertText(doc.getDocLength(), code, DEFAULT_STYLE);
    model.interpretCurrentInteraction();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ie) {
    }
    assertTrue(
        ("Code '" + code) + "' should generate a continuation exception but not a syntax exception",
        (model.isContinuationException() == true) && (model.isSyntaxException() == false));
  }
}
