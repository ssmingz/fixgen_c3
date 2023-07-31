class PlaceHold {
  protected void _assertProcessedContents(String typed, String expected)
      throws DocumentAdapterException {
    assert _model instanceof TestInteractionsModel;
    TestInteractionsModel model = ((TestInteractionsModel) (_model));
    InteractionsDocument doc = model.getDocument();
    doc.reset();
    doc.insertText(doc.getDocLength(), typed, DEFAULT_STYLE);
    model.interpretCurrentInteraction();
    assertEquals("processed output should match expected", expected, model.toEval);
  }
}
