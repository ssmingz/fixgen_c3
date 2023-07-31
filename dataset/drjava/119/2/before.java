class PlaceHold {
  @Override
  public Type visit(SuperFieldAccess node) {
    DJClass c = resolveThis(node.getClassName(), node);
    Type t = c.immediateSuperclass();
    if (t == null) {
      throw new ExecutionError("super.undefined", node);
    }
    Expression obj = TypeUtil.makeEmptyExpression(node);
    setType(obj, t);
    try {
      FieldReference ref = ts.lookupField(obj, node.getFieldName());
      checkAccessibility(ref.field(), node);
      setField(node, ref.field());
      setDJClass(node, c);
      setVariableType(node, ref.type());
      Type result = ts.capture(ref.type());
      addRuntimeCheck(node, result, ref.field().type());
      return setType(node, result);
    } catch (UnmatchedLookupException e) {
      setErrorStrings(node, ts.userRepresentation(t), node.getFieldName());
      if (e.matches() > 1) {
        throw new ExecutionError("ambiguous.field", node);
      } else {
        throw new ExecutionError("no.such.field", node);
      }
    }
  }
}
