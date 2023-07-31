class PlaceHold {
  public Iterable<VariableType> declaredTypeParameters() {
    Iterable<TypeParameter> paramAsts = IterUtil.empty();
    if (_ast instanceof GenericClassDeclaration) {
      paramAsts = IterUtil.asIterable(((GenericClassDeclaration) (_ast)).getTypeParameters());
    } else if (_ast instanceof GenericInterfaceDeclaration) {
      paramAsts = IterUtil.asIterable(((GenericClassDeclaration) (_ast)).getTypeParameters());
    }
    return IterUtil.mapSnapshot(paramAsts, NODE_TYPE_VARIABLE);
  }
}
