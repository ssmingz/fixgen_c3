class PlaceHold {
  protected void assertReplThrewSyntaxException(String code) throws DocumentAdapterException {
    assertTrue(_model instanceof IncompleteInputInteractionsModel);
    IncompleteInputInteractionsModel model = ((IncompleteInputInteractionsModel) (_model));
    InteractionsDocument doc = model.getDocument();
    doc.insertText(doc.getDocLength(), code, DEFAULT_STYLE);
    model.interpretCurrentInteraction();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ie) {
    }
    assertTrue(
        ("Code '" + code) + "' should generate a syntax exception but not a continuation exception",
        (model.isSyntaxException() == true) && (model.isContinuationException() == false));
  }
}
