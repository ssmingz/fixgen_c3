class PlaceHold {
  @Override
  public Type visit(ObjectMethodCall node) {
    Expression receiver = node.getExpression();
    if (receiver instanceof AmbiguousName) {
      Node resolved = resolveAmbiguousName(((AmbiguousName) (receiver)));
      if (resolved instanceof ReferenceTypeName) {
        Expression translation;
        if (node instanceof PolymorphicObjectMethodCall) {
          translation =
              new PolymorphicStaticMethodCall(
                  ((ReferenceTypeName) (resolved)),
                  node.getMethodName(),
                  node.getArguments(),
                  ((PolymorphicObjectMethodCall) (node)).getTypeArguments(),
                  node.getSourceInfo());
        } else {
          translation =
              new StaticMethodCall(
                  ((ReferenceTypeName) (resolved)),
                  node.getMethodName(),
                  node.getArguments(),
                  node.getSourceInfo());
        }
        translation.acceptVisitor(this);
        setTranslation(node, translation);
        return setType(node, getType(translation));
      } else {
        receiver = ((Expression) (resolved));
      }
    }
    Type receiverT = check(receiver);
    Iterable<? extends Expression> args = node.getArguments();
    checkList(args);
    Iterable<Type> targs = IterUtil.empty();
    if (node instanceof PolymorphicObjectMethodCall) {
      targs = checkTypeNameList(((PolymorphicObjectMethodCall) (node)).getTypeArguments());
    }
    try {
      ObjectMethodInvocation inv =
          ts.lookupMethod(receiver, node.getMethodName(), targs, args, expected);
      checkAccessibility(inv.method(), node);
      checkThrownExceptions(inv.thrown(), node);
      node.setExpression(inv.object());
      node.setArguments(CollectUtil.makeList(inv.args()));
      setMethod(node, inv.method());
      Type result = ts.capture(inv.returnType());
      debug.logValue("Type of method call " + node.getMethodName(), ts.wrap(result));
      addRuntimeCheck(node, result, inv.method().returnType());
      return setType(node, result);
    } catch (InvalidTypeArgumentException e) {
      throw new ExecutionError("type.argument", node);
    } catch (TypeSystemException e) {
      setErrorStrings(
          node, ts.userRepresentation(receiverT), node.getMethodName(), nodeTypesString(args));
      throw new ExecutionError("no.such.method", node);
    }
  }
}
