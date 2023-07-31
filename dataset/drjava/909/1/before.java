class PlaceHold {
  public void testBinaryOpExpression() throws ParseException {
    List<IdentifierToken> idnt = new LinkedList<IdentifierToken>();
    idnt.add(new Identifier("i"));
    expectedAST.add(new ShiftLeftExpression(new QualifiedName(idnt), new IntegerLiteral("5")));
    verifyOutput(" (i) << (5) ", expectedAST);
  }
}
