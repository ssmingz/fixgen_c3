class PlaceHold {
  public void testArrayAccess() throws ParseException {
    expectedAST.add(new ArrayAccess(new AmbiguousName("v"), new IntegerLiteral("5")));
    verifyOutput("v[5]", expectedAST);
  }
}
