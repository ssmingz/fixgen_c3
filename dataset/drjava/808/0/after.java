class PlaceHold {
  public void testStatementExpression() throws ParseException {
    expectedAST.add(
        new ExpressionStatement(
            new ObjectMethodCall(new AmbiguousName("o"), "m", null, "", -1, -1, -1, -1), true));
    verifyOutput("o.m();", expectedAST);
  }
}
