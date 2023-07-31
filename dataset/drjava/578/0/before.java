class PlaceHold {
  public void checkConstructorCall(ConstructorCall node) {
    if (node.getExpression() != null) {
      throw new ExecutionError("not.implemented", node);
    }
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    Type type;
    if (node.isSuper()) {
      type = context.getThis().immediateSuperclass();
    } else {
      type = SymbolUtil.thisType(context.getThis());
    }
    if (type == null) {
      throw new IllegalArgumentException("Can't check a ConstructorCall in this context");
    }
    try {
      ConstructorInvocation inv = ts.lookupConstructor(type, targs, args, Option.<Type>none());
      checkAccessibility(inv.constructor(), node, true);
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setConstructor(node, inv.constructor());
      setType(node, type);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (TypeSystemException e) {
      setErrorStrings(node, ts.userRepresentation(type), nodeTypesString(args));
      throw new ExecutionError("no.such.constructor", node);
    }
  }
}
