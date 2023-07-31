class PlaceHold {
  @Override
  public ClassType typeContainingMethod(String name, TypeSystem ts) throws AmbiguousNameException {
    DJClass explicitImport = _importedMethods.get(name);
    ClassType result = (explicitImport == null) ? null : ts.makeClassType(explicitImport);
    if (result == null) {
      LinkedList<ClassType> onDemandMatches = new LinkedList<ClassType>();
      for (DJClass c : _staticOnDemandClasses) {
        ClassType t = ts.makeClassType(c);
        if (ts.containsStaticMethod(t, name)) {
          onDemandMatches.add(t);
        }
      }
      if (onDemandMatches.size() > 1) {
        throw new AmbiguousNameException();
      } else if (onDemandMatches.size() == 1) {
        result = onDemandMatches.getFirst();
      }
      if (result == null) {
        result = super.typeContainingMethod(name, ts);
      }
    }
    return result;
  }
}
