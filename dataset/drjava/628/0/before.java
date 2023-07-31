class PlaceHold {
  private Class visitBitwiseAssign(BinaryExpression node) {
    Node ln = node.getLeftExpression();
    Class lc = ln.acceptVisitor(this);
    Class rc = node.getRightExpression().acceptVisitor(this);
    if (((((((((((lc == null) || (rc == null)) || (lc == void.class)) || (rc == void.class))
                                || (lc == float.class))
                            || (rc == float.class))
                        || (lc == double.class))
                    || (rc == double.class))
                || (((lc == boolean.class) || (lc == Boolean.class))
                    ^ ((rc == boolean.class) || (rc == Boolean.class))))
            || (!(lc.isPrimitive() || _isBoxingType(lc))))
        || (!(rc.isPrimitive() || _isBoxingType(rc)))) {
      throw new ExecutionError("bitwise.expression.type", node);
    }
    if (!ln.hasProperty(MODIFIER)) {
      throw new ExecutionError("left.expression", node);
    }
    node.setProperty(TYPE, lc);
    return lc;
  }
}
