class PlaceHold {
  public void testNormalAssignment() throws ParseException {
    List<IdentifierToken> idnt = new LinkedList<IdentifierToken>();
    idnt.add(new Identifier("i"));
    expectedAST.add(new SimpleAssignExpression(new QualifiedName(idnt), new IntegerLiteral("3")));
    verifyOutput(" (i = 3) ", expectedAST);
  }
}
