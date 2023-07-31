class PlaceHold {
  public void evaluateConstructorBody(String key, RuntimeBindings bindings, Object[] args)
      throws Throwable {
    ConstructorDeclaration decl = _constructors.get(key);
    RuntimeBindings constructorBindings = bindArgs(bindings, decl.getParameters(), args);
    evaluateBlock(new BlockStatement(decl.getStatements()), void.class, constructorBindings);
  }
}
