class PlaceHold {
  @Override
  public void leaveToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.LITERAL_IF:
        leaveLiteralIf(ast);
        break;
      default:
        throw new IllegalStateException(ast.toString());
    }
  }
}
