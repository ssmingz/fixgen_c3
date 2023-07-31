class PlaceHold {
  private boolean containsAnyVar(Type t, final Set<? extends VariableType> vars) {
    return t.apply(
        new TypeAbstractVisitor<Boolean>() {
          private final RecursionStack<Type> _stack = new RecursionStack<Type>();

          public Boolean defaultCase(Type t) {
            return false;
          }

          @Override
          public Boolean forArrayType(ArrayType t) {
            return t.ofType().apply(this);
          }

          @Override
          public Boolean forParameterizedClassType(ParameterizedClassType t) {
            return checkList(t.typeArguments());
          }

          @Override
          public Boolean forBoundType(BoundType t) {
            return checkList(t.ofTypes());
          }

          @Override
          public Boolean forVariableType(VariableType t) {
            return vars.contains(t) || checkBoundedSymbol(t, t.symbol());
          }

          @Override
          public Boolean forWildcard(Wildcard w) {
            return checkBoundedSymbol(w, w.symbol());
          }

          private Boolean checkList(Iterable<? extends Type> types) {
            for (Type t : types) {
              if (t.apply(this)) {
                return true;
              }
            }
            return false;
          }

          private Boolean checkBoundedSymbol(Type t, final BoundedSymbol s) {
            final TypeVisitor<Boolean> visitor = this;
            Thunk<Boolean> handleBounds =
                new Thunk<Boolean>() {
                  public Boolean value() {
                    return s.lowerBound().apply(visitor) || s.upperBound().apply(visitor);
                  }
                };
            return _stack.apply(handleBounds, false, t);
          }
        });
  }
}
