class PlaceHold {
  @Override
  public void leaveToken(DetailAST ast) {
    if (ast.getType() == TokenTypes.LITERAL_IF) {
      leaveLiteralIf(ast);
    } else {
      throw new IllegalStateException(ast.toString());
    }
  }
}
