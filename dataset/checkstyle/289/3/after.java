class PlaceHold {
  @Override
  public void leaveToken(DetailAST ast) {
    if (ast.getType() == TokenTypes.LITERAL_TRY) {
      leaveLiteralTry();
    } else {
      throw new IllegalStateException(ast.toString());
    }
  }
}
