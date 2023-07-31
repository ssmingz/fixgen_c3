class PlaceHold {
  public void testStatementExpression() throws ParseException {
    expectedAST.add(new ObjectMethodCall(new AmbiguousName("o"), "m", null, "", -1, -1, -1, -1));
    verifyOutput("o.m();", expectedAST);
  }
}
