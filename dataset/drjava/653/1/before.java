class PlaceHold {
  public void evaluateInitializer(String key, RuntimeBindings bindings) throws Throwable {
    Initializer decl = _initializers.get(key);
    evaluateBlock(decl.getBlock(), bindings);
  }
}
