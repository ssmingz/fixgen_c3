class PlaceHold {
  @Override
  public Type visit(SimpleMethodCall node) {
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    ClassType t;
    if (context.localFunctionExists(node.getMethodName(), ts)) {
      Iterable<LocalFunction> matches = context.getLocalFunctions(node.getMethodName(), ts);
      t = ts.makeClassType(new FunctionWrapperClass(context.accessModule(), matches));
    } else {
      try {
        t = context.typeContainingMethod(node.getMethodName(), ts);
        if (t == null) {
          setErrorStrings(node, node.getMethodName());
          throw new ExecutionError("undefined.name", node);
        }
      } catch (AmbiguousNameException e) {
        throw new ExecutionError("ambiguous.name", node);
      }
    }
    try {
      MethodInvocation inv;
      if (context.getThis() == null) {
        inv = ts.lookupStaticMethod(t, node.getMethodName(), targs, args, expected);
      } else {
        Expression obj = TypeUtil.makeEmptyExpression(node);
        setType(obj, t);
        inv = ts.lookupMethod(obj, node.getMethodName(), targs, args, expected);
      }
      checkAccessibility(inv.method(), node);
      checkThrownExceptions(inv.thrown(), node);
      node.setArguments(CollectUtil.makeList(inv.args()));
      setMethod(node, inv.method());
      if (!inv.method().isStatic()) {
        setDJClass(node, t.ofClass());
      }
      Type result = ts.capture(inv.returnType());
      debug.logValue("Type of method call " + node.getMethodName(), ts.wrap(result));
      addRuntimeCheck(node, result, inv.method().returnType());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (UnmatchedLookupException e) {
      setErrorStrings(node, ts.userRepresentation(t), node.getMethodName(), nodeTypesString(args));
      if (e.matches() > 1) {
        throw new ExecutionError("ambiguous.method", node);
      } else {
        throw new ExecutionError("no.such.method", node);
      }
    }
  }
}
