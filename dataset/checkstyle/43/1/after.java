class PlaceHold {
  public void checkCount(DetailAST ast) {
    if (checking && (count > getMax())) {
      log(ast.getLineNo(), ast.getColumnNo(), MSG_KEY, count, getMax());
    }
  }
}
