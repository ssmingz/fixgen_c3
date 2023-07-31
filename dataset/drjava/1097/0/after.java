class PlaceHold {
  @Override
  public Type visit(SuperMethodCall node) {
    DJClass c = resolveThis(node.getClassName(), node);
    Type t = c.immediateSuperclass();
    if (t == null) {
      throw new ExecutionError("super.undefined", node);
    }
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    if (node instanceof PolymorphicSuperMethodCall) {
      targs = checkTypeNameList(((PolymorphicSuperMethodCall) (node)).getTypeArguments());
    }
    Expression obj = TypeUtil.makeEmptyExpression(node);
    setType(obj, t);
    try {
      MethodInvocation inv = ts.lookupMethod(obj, node.getMethodName(), targs, args, expected);
      checkAccessibility(inv.method(), node);
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setMethod(node, inv.method());
      setDJClass(node, c);
      Type result = ts.capture(inv.returnType());
      debug.logValue("Type of method call " + node.getMethodName(), ts.wrap(result));
      addRuntimeCheck(node, result, inv.method().returnType());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (UnmatchedLookupException e) {
      throw unmatchedFunctionError(
          "method", e, node, t, node.getMethodName(), targs, args, expected, false);
    }
  }
}
