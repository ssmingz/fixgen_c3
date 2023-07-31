class PlaceHold {
  @Override
  public Type visit(StaticFieldAccess node) {
    Type t = checkTypeName(node.getFieldType());
    try {
      FieldReference ref = ts.lookupStaticField(t, node.getFieldName());
      checkAccessibility(ref.field(), node);
      setField(node, ref.field());
      setVariableType(node, ref.type());
      Type result = ts.capture(ref.type());
      addRuntimeCheck(node, result, ref.field().type());
      return setType(node, result);
    } catch (UnmatchedLookupException e) {
      setErrorStrings(node, ts.userRepresentation(t), node.getFieldName());
      if (e.matches() > 1) {
        throw new ExecutionError("ambiguous.field", node);
      } else {
        throw new ExecutionError("no.such.static.field", node);
      }
    }
  }
}
