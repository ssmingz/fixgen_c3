class PlaceHold {
  public void testInstanceAllocationExpression() throws ParseException {
    List<Expression> args = new LinkedList<Expression>();
    args.add(new IntegerLiteral("3"));
    expectedAST.add(new SimpleAllocation(new ReferenceTypeName("C"), args));
    verifyOutput(" new C(3) ", expectedAST);
  }
}
