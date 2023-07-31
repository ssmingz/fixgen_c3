class PlaceHold {
  @Override
  public Void visit(MethodDeclaration node) {
    DJMethod m = getMethod(node);
    TypeParameter[] tparams;
    if (node instanceof PolymorphicMethodDeclaration) {
      tparams = ((PolymorphicMethodDeclaration) (node)).getTypeParameters();
    } else {
      tparams = new TypeParameter[0];
    }
    for (TypeParameter tparam : tparams) {
      setTypeVariable(
          tparam, new VariableType(new BoundedSymbol(tparam, tparam.getRepresentation())));
    }
    TypeContext sigContext = new FunctionSignatureContext(_context, m);
    ExpressionChecker sigChecker = new ExpressionChecker(sigContext, _opt);
    sigChecker.setTypeParameterBounds(tparams);
    node.getReturnType().acceptVisitor(sigChecker);
    for (FormalParameter param : node.getParameters()) {
      Type t = param.getType().acceptVisitor(sigChecker);
      setVariable(param, new LocalVariable(param.getName(), t, param.isFinal()));
    }
    for (TypeName tn : node.getExceptions()) {
      tn.acceptVisitor(sigChecker);
    }
    TypeContext bodyContext = new FunctionContext(sigContext, m);
    node.getBody().acceptVisitor(new StatementChecker(bodyContext, _opt));
    return null;
  }
}
