class PlaceHold {
  private void checkEqualityStaticRules(Class lc, Class rc, BinaryExpression n) {
    Expression leftExp = n.getLeftExpression();
    Expression rightExp = n.getRightExpression();
    if ((lc != null) && (rc != null)) {
      if (_isBoxingType(lc) && rc.isPrimitive()) {
        ObjectMethodCall methodCall = _unbox(leftExp, lc);
        n.setLeftExpression(methodCall);
        lc = ((Class) (methodCall.getProperty(TYPE)));
      }
      if (_isBoxingType(rc) && lc.isPrimitive()) {
        ObjectMethodCall methodCall = _unbox(rightExp, rc);
        n.setRightExpression(methodCall);
        rc = ((Class) (methodCall.getProperty(TYPE)));
      }
    }
    if ((lc != rc) || (lc == void.class)) {
      if ((((lc == void.class) || (rc == void.class)) || (lc == boolean.class))
          || (rc == boolean.class)) {
        throw new ExecutionError("compare.type", n);
      } else if (((lc == null) && rc.isPrimitive()) || ((rc == null) && lc.isPrimitive())) {
        throw new ExecutionError("compare.type", n);
      } else if ((lc != null) && (rc != null)) {
        if (lc.isPrimitive() ^ rc.isPrimitive()) {
          throw new ExecutionError("compare.type", n);
        } else if ((!lc.isPrimitive()) && (!rc.isPrimitive())) {
          if ((!lc.isAssignableFrom(rc)) && (!rc.isAssignableFrom(lc))) {
            throw new ExecutionError("compare.type", n);
          }
        }
      }
    }
  }
}
