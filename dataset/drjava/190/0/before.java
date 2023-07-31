class PlaceHold {
  public void testDotClass() throws ParseException {
    expectedAST.add(new TypeExpression(new ReferenceType("List")));
    verifyOutput("List.class", expectedAST);
  }
}
