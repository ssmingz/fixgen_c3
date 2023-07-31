class PlaceHold {
  public void testBinaryOpExpression() throws ParseException {
    expectedAST.add(new ShiftLeftExpression(new AmbiguousName("i"), new IntegerLiteral("5")));
    verifyOutput(" (i) << (5) ", expectedAST);
  }
}
