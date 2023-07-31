class PlaceHold {
  public void testInstanceOfExpression() throws ParseException {
    List<IdentifierToken> idnt = new LinkedList<IdentifierToken>();
    idnt.add(new Identifier("v"));
    expectedAST.add(
        new InstanceOfExpression(new QualifiedName(idnt), new ReferenceTypeName("String")));
    verifyOutput("((v) instanceof String)", expectedAST);
  }
}
