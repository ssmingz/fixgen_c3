class PlaceHold {
  public void testArrayAccess() throws ParseException {
    List<IdentifierToken> ident = new LinkedList<IdentifierToken>();
    ident.add(new Identifier("v"));
    expectedAST.add(new ArrayAccess(new QualifiedName(ident), new IntegerLiteral("5")));
    verifyOutput("v[5]", expectedAST);
  }
}
