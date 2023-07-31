class PlaceHold {
  @Override
  public void visitToken(DetailAST ast) {
    if (ast.getType() == TokenTypes.LITERAL_IF) {
      visitLiteralIf(ast);
    } else {
      throw new IllegalStateException(ast.toString());
    }
  }
}
