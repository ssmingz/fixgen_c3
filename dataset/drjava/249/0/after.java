class PlaceHold {
  public void testDotThis() throws ParseException {
    List<IdentifierToken> ident = new LinkedList<IdentifierToken>();
    ident.add(new Identifier("List"));
    Expression expected = new ThisExpression(ident, SourceInfo.NONE);
    verifyExprOutput("List.this", expected);
  }
}
