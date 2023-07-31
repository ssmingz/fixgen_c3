class PlaceHold {
  public Void forComplexAnonymousClassInstantiation(ComplexAnonymousClassInstantiation that) {
    SymbolData enclosing = getQualifiedSymbolData(_enclosingClassName);
    assert enclosing != null;
    complexAnonymousClassInstantiationHelper(that, enclosing);
    return null;
  }
}
