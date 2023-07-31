class PlaceHold {
  public void testInnerInstanceAllocationExpression() throws ParseException {
    List<IdentifierToken> idnt = new LinkedList<IdentifierToken>();
    idnt.add(new Identifier("list"));
    List<Expression> args = new LinkedList<Expression>();
    args.add(new IntegerLiteral("3"));
    expectedAST.add(
        new InnerAllocation(new QualifiedName(idnt), new ReferenceType("Iterator"), args));
    verifyOutput("list.new Iterator(3)", expectedAST);
  }
}
