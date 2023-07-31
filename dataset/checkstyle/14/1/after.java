class PlaceHold {
  public void visitToken(DetailAST aAST) {
    if (ScopeUtils.inInterfaceOrAnnotationBlock(aAST)) {
      return;
    }
    final DetailAST modifiers = aAST.findFirstToken(MODIFIERS);
    if (((modifiers.branchContains(LITERAL_PRIVATE) || modifiers.branchContains(ABSTRACT))
            || modifiers.branchContains(FINAL))
        || modifiers.branchContains(LITERAL_STATIC)) {
      return;
    }
    if (!ScopeUtils.getSurroundingScope(aAST).isIn(PROTECTED)) {
      return;
    }
    final DetailAST implementation = aAST.findFirstToken(SLIST);
    if ((implementation != null)
        && (implementation.getFirstChild().getType() == TokenTypes.RCURLY)) {
      return;
    }
    final DetailAST classDef = findContainingClass(aAST);
    final DetailAST classMods = classDef.findFirstToken(MODIFIERS);
    if (classMods.branchContains(FINAL)) {
      return;
    }
    final DetailAST objBlock = classDef.findFirstToken(OBJBLOCK);
    boolean hasDefaultConstructor = true;
    boolean hasExplNonPrivateCtor = false;
    DetailAST candidate = ((DetailAST) (objBlock.getFirstChild()));
    while (candidate != null) {
      if (candidate.getType() == TokenTypes.CTOR_DEF) {
        hasDefaultConstructor = false;
        final DetailAST ctorMods = candidate.findFirstToken(MODIFIERS);
        if (!ctorMods.branchContains(LITERAL_PRIVATE)) {
          hasExplNonPrivateCtor = true;
          break;
        }
      }
      candidate = ((DetailAST) (candidate.getNextSibling()));
    }
    if (hasDefaultConstructor || hasExplNonPrivateCtor) {
      final String name = aAST.findFirstToken(IDENT).getText();
      log(aAST.getLineNo(), aAST.getColumnNo(), "design.forExtension", name);
    }
  }
}
