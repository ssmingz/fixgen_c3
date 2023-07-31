class PlaceHold {
  @Override
  public Type visit(ObjectFieldAccess node) {
    Expression receiver = node.getExpression();
    Type receiverT = check(receiver);
    try {
      ObjectFieldReference ref =
          ts.lookupField(receiver, node.getFieldName(), context.accessModule());
      node.setExpression(ref.object());
      setField(node, ref.field());
      setVariableType(node, ref.type());
      Type result = ts.capture(ref.type());
      addRuntimeCheck(node, result, ref.field().type());
      return setType(node, result);
    } catch (UnmatchedLookupException e) {
      setErrorStrings(node, ts.userRepresentation(receiverT), node.getFieldName());
      if (e.matches() > 1) {
        throw new ExecutionError("ambiguous.field", node);
      } else {
        throw new ExecutionError("no.such.field", node);
      }
    }
  }
}
