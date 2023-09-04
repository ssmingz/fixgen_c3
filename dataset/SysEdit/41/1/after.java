class PlaceHold {
  public ContinueStatement convert(
      org.eclipse.jdt.internal.compiler.ast.ContinueStatement statement) {
    ContinueStatement continueStatement = new ContinueStatement(this.ast);
    continueStatement.setSourceRange(
        statement.sourceStart, statement.sourceEnd - statement.sourceStart + 1);
    if (statement.label != null) {
      final SimpleName name = new SimpleName(this.ast);
      name.internalSetIdentifier(new String(statement.label));
      retrieveIdentifierAndSetPositions(statement.sourceStart, statement.sourceEnd, name);
      continueStatement.setLabel(name);
    }
    retrieveSemiColonPosition(continueStatement);
    return continueStatement;
  }
}
