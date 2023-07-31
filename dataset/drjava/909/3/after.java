class PlaceHold {
  public void testNormalAssignment() throws ParseException {
    expectedAST.add(new SimpleAssignExpression(new AmbiguousName("i"), new IntegerLiteral("3")));
    verifyOutput(" (i = 3) ", expectedAST);
  }
}
