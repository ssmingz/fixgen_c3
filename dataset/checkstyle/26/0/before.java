class PlaceHold {
  @Override
  public void visitToken(DetailAST ast) {
    log(ast.getLineNo(), ast.getColumnNo(), "empty.statement");
  }
}
