class PlaceHold {
  @Override
  public void visitToken(DetailAST ast) {
    switch (ast.getType()) {
      case TokenTypes.LITERAL_IF:
        visitLiteralIf(ast);
        break;
      default:
        throw new IllegalStateException(ast.toString());
    }
  }
}
