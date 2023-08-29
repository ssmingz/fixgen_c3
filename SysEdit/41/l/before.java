class PlaceHold {
  public BreakStatement convert(org.eclipse.jdt.internal.compiler.ast.BreakStatement statement) {
    BreakStatement breakStatement = this.ast.newBreakStatement();
    breakStatement.setSourceRange(
        statement.sourceStart, statement.sourceEnd - statement.sourceStart + 1);
    if (statement.label != null) {
      SimpleName name = this.ast.newSimpleName(new String(statement.label));
      retrieveIdentifierAndSetPositions(statement.sourceStart, statement.sourceEnd, name);
      breakStatement.setLabel(name);
    }
    retrieveSemiColonPosition(breakStatement);
    return breakStatement;
  }
}
