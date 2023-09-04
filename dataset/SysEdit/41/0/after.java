class PlaceHold {
  public BreakStatement convert(org.eclipse.jdt.internal.compiler.ast.BreakStatement statement) {
    BreakStatement breakStatement = new BreakStatement(this.ast);
    breakStatement.setSourceRange(
        statement.sourceStart, statement.sourceEnd - statement.sourceStart + 1);
    if (statement.label != null) {
      final SimpleName name = new SimpleName(this.ast);
      name.internalSetIdentifier(new String(statement.label));
      retrieveIdentifierAndSetPositions(statement.sourceStart, statement.sourceEnd, name);
      breakStatement.setLabel(name);
    }
    retrieveSemiColonPosition(breakStatement);
    return breakStatement;
  }
}
