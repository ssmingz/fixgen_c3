class PlaceHold {
  public void testDotClass() throws ParseException {
    expectedAST.add(new TypeExpression(new ReferenceTypeName("List")));
    verifyOutput("List.class", expectedAST);
  }
}
