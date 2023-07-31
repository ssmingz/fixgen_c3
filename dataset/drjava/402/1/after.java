class PlaceHold {
  protected Type immediateSupertype(ParameterizedClassType t) {
    ParameterizedClassType tCap = capture(t);
    DJClass c = tCap.ofClass();
    Iterable<? extends Type> sups =
        substitute(c.declaredSupertypes(), SymbolUtil.allTypeParameters(c), tCap.typeArguments());
    return new IntersectionType(IterUtil.compose(OBJECT, sups));
  }
}
