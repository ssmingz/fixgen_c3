class PlaceHold {
  public void testMethodInvocation() throws ParseException {
    List<Expression> args = new LinkedList<Expression>();
    args.add(new SimpleAllocation(new ReferenceTypeName("Object"), null));
    args.add(new IntegerLiteral("5"));
    args.add(new BooleanLiteral(false));
    Expression expected =
        new ObjectMethodCall(new AmbiguousName("e"), "meth", args, null, -1, -1, -1, -1);
    verifyExprOutput("e.meth(new Object(), 5, false)", expected);
  }
}
