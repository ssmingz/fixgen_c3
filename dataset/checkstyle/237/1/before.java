class PlaceHold {
  public void leaveToken(DetailAST aAST) {
    if (isOverridingMethod(aAST)) {
      final MethodNode methodNode = ((MethodNode) (mMethodStack.removeLast()));
      if (!methodNode.getCallsSuper()) {
        final DetailAST methodAST = methodNode.getMethod();
        final DetailAST nameAST = methodAST.findFirstToken(IDENT);
        log(
            nameAST.getLineNo(),
            nameAST.getColumnNo(),
            "missing.super.call",
            new Object[] {nameAST.getText()});
      }
    }
  }
}
