class PlaceHold {
  public void testMethodInvocation() throws ParseException {
    List<IdentifierToken> ident = new LinkedList<IdentifierToken>();
    ident.add(new Identifier("e"));
    List<Expression> args = new LinkedList<Expression>();
    args.add(new SimpleAllocation(new ReferenceType("Object"), null));
    args.add(new IntegerLiteral("5"));
    args.add(new BooleanLiteral(false));
    expectedAST.add(
        new ObjectMethodCall(new QualifiedName(ident), "meth", args, null, -1, -1, -1, -1));
    verifyOutput("e.meth(new Object(), 5, false)", expectedAST);
  }
}
