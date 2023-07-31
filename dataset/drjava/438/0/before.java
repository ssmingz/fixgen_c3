class PlaceHold {
  public void addInterpreter(
      String name,
      Object thisVal,
      Class<?> thisClass,
      Object[] localVars,
      String[] localVarNames,
      Class<?>[] localVarClasses) {
    debug.logValues(
        new String[] {
          "name", "thisVal", "thisClass", "localVars", "localVarNames", "localVarClasses"
        },
        name,
        thisVal,
        thisClass,
        localVars,
        localVarNames,
        localVarClasses);
    if (_interpreters.containsKey(name)) {
      throw new IllegalArgumentException(("'" + name) + "' is not a unique interpreter name");
    }
    if ((localVars.length != localVarNames.length)
        || (localVars.length != localVarClasses.length)) {
      throw new IllegalArgumentException("Local variable arrays are inconsistent");
    }
    Package pkg = thisClass.getPackage();
    DJClass c = SymbolUtil.wrapClass(thisClass);
    List<LocalVariable> vars = new LinkedList<LocalVariable>();
    for (int i = 0; i < localVars.length; i++) {
      if (localVarClasses[i] == null) {
        try {
          localVarClasses[i] = ((Class<?>) (localVars[i].getClass().getField("TYPE").get(null)));
        } catch (IllegalAccessException e) {
          throw new IllegalArgumentException(e);
        } catch (NoSuchFieldException e) {
          throw new IllegalArgumentException(e);
        }
      }
      Type varT =
          SymbolUtil.typeOfGeneralClass(localVarClasses[i], _interpreterOptions.typeSystem());
      vars.add(new LocalVariable(localVarNames[i], varT, false));
    }
    TypeContext ctx = new TopLevelContext(_interpreterLoader);
    if (pkg != null) {
      ctx = ctx.setPackage(pkg.getName());
    }
    ctx = new ClassSignatureContext(ctx, c, _interpreterLoader);
    ctx = new ClassContext(ctx, c);
    ctx = new DebugMethodContext(ctx, thisVal == null);
    ctx = new LocalContext(ctx, vars);
    RuntimeBindings bindings = RuntimeBindings.EMPTY;
    if (thisVal != null) {
      bindings = new RuntimeBindings(bindings, c, thisVal);
    }
    bindings = new RuntimeBindings(bindings, vars, IterUtil.make(localVars));
    Interpreter i = new Interpreter(_interpreterOptions, ctx, bindings);
    _environments.put(name, Pair.make(ctx, bindings));
    _interpreters.put(name, i);
  }
}
