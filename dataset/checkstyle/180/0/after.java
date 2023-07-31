class PlaceHold {
  protected final boolean mustCheckName(DetailAST aAST) {
    DetailAST modifiersAST = aAST.findFirstToken(MODIFIERS);
    final boolean isStatic = (modifiersAST != null) && modifiersAST.branchContains(LITERAL_STATIC);
    return ((!isStatic) && (!ScopeUtils.inInterfaceBlock(aAST)))
        && (!ScopeUtils.isLocalVariableDef(aAST));
  }
}
