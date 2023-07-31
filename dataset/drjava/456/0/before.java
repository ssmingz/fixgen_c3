class PlaceHold {
  public ClassType typeContainingMemberClass(String name, TypeSystem ts)
      throws AmbiguousNameException {
    DJClass explicitImport = _importedMemberClasses.get(name);
    ClassType result = (explicitImport == null) ? null : ts.makeClassType(explicitImport);
    if (result == null) {
      LinkedList<ClassType> onDemandMatches = new LinkedList<ClassType>();
      for (DJClass c : _onDemandClasses) {
        ClassType t = ts.makeClassType(c);
        if (ts.containsClass(t, name)) {
          onDemandMatches.add(t);
        }
      }
      for (DJClass c : _staticOnDemandClasses) {
        ClassType t = ts.makeClassType(c);
        if (ts.containsStaticClass(t, name)) {
          onDemandMatches.add(t);
        }
      }
      if (onDemandMatches.size() > 1) {
        throw new AmbiguousNameException();
      } else if (onDemandMatches.size() == 1) {
        result = onDemandMatches.getFirst();
      }
    }
    return result;
  }
}
