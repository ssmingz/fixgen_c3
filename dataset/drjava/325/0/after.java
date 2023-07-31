class PlaceHold {
  public void testInsertNewlineEndQuote() {
    model1.insertChar('\"');
    insertGap(model1, 5);
    model1.move(-2);
    model1.insertChar('\n');
    model1.move(-1);
    assertEquals("#0.4", FREE, model1.currentToken().getState());
    assertEquals("#0.2", "\n", model1.currentToken().getType());
    model1.move(1);
    assertEquals("#0.0", FREE, model1._getStateAtCurrent());
    assertTrue("#0.1", model1.currentToken().isGap());
    assertEquals("#0.3", 2, model1.currentToken().getSize());
    assertEquals("#0.5", FREE, model1.currentToken().getState());
  }
}
