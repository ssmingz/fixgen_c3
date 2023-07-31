class PlaceHold {
  @Override
  public void visitToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.LITERAL_TRY:
        visitLiteralTry(ast);
        break;
      default:
        throw new IllegalStateException(ast.toString());
    }
  }
}
