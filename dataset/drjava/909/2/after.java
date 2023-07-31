class PlaceHold {
  public void testInstanceOfExpression() throws ParseException {
    expectedAST.add(
        new InstanceOfExpression(new AmbiguousName("v"), new ReferenceTypeName("String")));
    verifyOutput("((v) instanceof String)", expectedAST);
  }
}
