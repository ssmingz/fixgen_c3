class PlaceHold {
  @Override
  public void leaveToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.LITERAL_TRY:
        leaveLiteralTry();
        break;
      default:
        throw new IllegalStateException(ast.toString());
    }
  }
}
