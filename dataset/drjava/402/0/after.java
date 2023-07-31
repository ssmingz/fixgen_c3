class PlaceHold {
  protected Type immediateSupertype(RawClassType t) {
    Iterable<Type> erasedSups =
        IterUtil.map(
            t.ofClass().declaredSupertypes(),
            new Lambda<Type, Type>() {
              public Type value(Type t) {
                return t.apply(ERASE);
              }
            });
    return new IntersectionType(IterUtil.compose(OBJECT, erasedSups));
  }
}
