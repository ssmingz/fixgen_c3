class PlaceHold {
  public Object visit(ObjectMethodCall node) {
    List args = node.getArguments();
    if (args != null) {
      visitList(args);
    }
    Expression exp = node.getExpression();
    Object o;
    if (exp == null) {
      o = context.getDefaultQualifier(node);
    } else {
      o = exp.acceptVisitor(this);
      if (o == null) {
        return null;
      }
    }
    if (o == null) {
      return new FunctionCall(
          node.getMethodName(),
          node.getArguments(),
          node.getFilename(),
          node.getBeginLine(),
          node.getBeginColumn(),
          node.getEndLine(),
          node.getEndColumn());
    } else if (o instanceof ReferenceType) {
      return new StaticMethodCall(
          ((ReferenceType) (o)),
          node.getMethodName(),
          node.getArguments(),
          node.getFilename(),
          node.getBeginLine(),
          node.getBeginColumn(),
          node.getEndLine(),
          node.getEndColumn());
    } else {
      node.setExpression(((Expression) (o)));
    }
    return null;
  }
}
