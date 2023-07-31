class PlaceHold {
  public Class visit(DivideAssignExpression node) {
    Node ln = node.getLeftExpression();
    Class lc = ln.acceptVisitor(this);
    Class rc = node.getRightExpression().acceptVisitor(this);
    if ((((((((((lc == null) || (rc == null)) || (lc == void.class)) || (rc == void.class))
                            || (lc == boolean.class))
                        || (rc == boolean.class))
                    || (lc == Boolean.class))
                || (rc == Boolean.class))
            || (!(lc.isPrimitive() || _isBoxingType(lc))))
        || (!(rc.isPrimitive() || _isBoxingType(rc)))) {
      throw new ExecutionError("division.type", node);
    }
    if (!ln.hasProperty(MODIFIER)) {
      throw new ExecutionError("left.expression", node);
    }
    node.setProperty(TYPE, lc);
    return lc;
  }
}
