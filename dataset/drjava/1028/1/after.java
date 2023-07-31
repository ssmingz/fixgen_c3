class PlaceHold {
  @Override
  public TypeContext visit(MethodDeclaration node) {
    LocalFunction f = new LocalFunction(node);
    TypeParameter[] tparams;
    if (node instanceof PolymorphicMethodDeclaration) {
      tparams = ((PolymorphicMethodDeclaration) (node)).getTypeParameters();
    } else {
      tparams = new TypeParameter[0];
    }
    for (TypeParameter param : tparams) {
      setTypeVariable(param, new VariableType(new BoundedSymbol(param, param.getRepresentation())));
    }
    TypeContext sigContext = new FunctionSignatureContext(context, f);
    ExpressionChecker sigChecker = new ExpressionChecker(sigContext, opt);
    sigChecker.setTypeParameterBounds(tparams);
    Type returnT = node.getReturnType().acceptVisitor(sigChecker);
    setErasedType(node, ts.erasedClass(returnT));
    for (FormalParameter p : node.getParameters()) {
      Type t = p.getType().acceptVisitor(sigChecker);
      setVariable(p, new LocalVariable(p.getName(), t, p.isFinal()));
    }
    for (ReferenceTypeName n : node.getExceptions()) {
      n.acceptVisitor(sigChecker);
    }
    TypeContext bodyContext = new FunctionContext(sigContext, f);
    node.getBody().acceptVisitor(new StatementChecker(bodyContext, opt));
    return new LocalContext(context, f);
  }
}
