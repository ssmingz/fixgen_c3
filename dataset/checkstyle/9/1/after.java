class PlaceHold {
  @Override
  public void visitToken(DetailAST ast) {
    if (ast.getType() == TokenTypes.LITERAL_TRY) {
      visitLiteralTry(ast);
    } else {
      throw new IllegalStateException(ast.toString());
    }
  }
}
