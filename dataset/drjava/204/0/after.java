class PlaceHold {
  public Void forSimpleAnonymousClassInstantiation(SimpleAnonymousClassInstantiation that) {
    SymbolData enclosing = getQualifiedSymbolData(_enclosingClassName);
    assert enclosing != null;
    simpleAnonymousClassInstantiationHelper(that, enclosing);
    return null;
  }
}
