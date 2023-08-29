class PlaceHold {
  public ContinueStatement convert(
      org.eclipse.jdt.internal.compiler.ast.ContinueStatement statement) {
    ContinueStatement continueStatement = this.ast.newContinueStatement();
    continueStatement.setSourceRange(
        statement.sourceStart, statement.sourceEnd - statement.sourceStart + 1);
    if (statement.label != null) {
      SimpleName name = this.ast.newSimpleName(new String(statement.label));
      retrieveIdentifierAndSetPositions(statement.sourceStart, statement.sourceEnd, name);
      continueStatement.setLabel(name);
    }
    retrieveSemiColonPosition(continueStatement);
    return continueStatement;
  }
}
