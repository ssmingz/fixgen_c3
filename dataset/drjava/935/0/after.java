class PlaceHold {
  @Override
  public Type visit(StaticMethodCall node) {
    Type t = checkTypeName(node.getMethodType());
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    if (node instanceof PolymorphicStaticMethodCall) {
      targs = checkTypeNameList(((PolymorphicStaticMethodCall) (node)).getTypeArguments());
    }
    try {
      MethodInvocation inv =
          ts.lookupStaticMethod(
              t, node.getMethodName(), targs, args, expected, context.accessModule());
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setMethod(node, inv.method());
      Type result = ts.capture(inv.returnType());
      debug.logValue("Type of method call " + node.getMethodName(), ts.wrap(result));
      addRuntimeCheck(node, result, inv.method().returnType());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (UnmatchedLookupException e) {
      throw unmatchedFunctionError(
          "method", e, node, t, node.getMethodName(), targs, args, expected, true);
    }
  }
}
