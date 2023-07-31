class PlaceHold {
  @Override
  public Type visit(ConstructorCall node) {
    if (node.getExpression() != null) {
      throw new ExecutionError("not.implemented", node);
    }
    Iterable<? extends Expression> args = IterUtil.empty();
    if (node.getArguments() != null) {
      args = node.getArguments();
      checkList(args);
    }
    Iterable<Type> targs = IterUtil.empty();
    Type result;
    if (node.isSuper()) {
      result = context.getSuperType(ts);
    } else {
      result = SymbolUtil.thisType(context.getThis());
    }
    if (result == null) {
      throw new IllegalArgumentException("Can't check a ConstructorCall in this context");
    }
    try {
      ConstructorInvocation inv = ts.lookupConstructor(result, targs, args, expected);
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setConstructor(node, inv.constructor());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (TypeSystemException e) {
      setErrorStrings(node, ts.userRepresentation(result), nodeTypesString(args));
      throw new ExecutionError("no.such.constructor", node);
    }
  }
}
