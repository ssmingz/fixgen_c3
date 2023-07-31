class PlaceHold {
  public Void forSourceFile(SourceFile that) {
    forSourceFileDoFirst(that);
    if (prune(that)) {
      return null;
    }
    for (int i = 0; i < that.getPackageStatements().length; i++) {
      that.getPackageStatements()[i].visit(this);
    }
    for (int i = 0; i < that.getImportStatements().length; i++) {
      that.getImportStatements()[i].visit(this);
    }
    if (!_importedPackages.contains("java.lang")) {
      _importedPackages.addFirst("java.lang");
    }
    TypeDefBase[] types = that.getTypes();
    _classNamesInThisFile = new LinkedList<String>();
    for (int i = 0; i < types.length; i++) {
      String qualifiedClassName = getQualifiedClassName(types[i].getName().getText());
      _classNamesInThisFile.addFirst(qualifiedClassName);
      _classesToBeParsed.put(
          qualifiedClassName, new Pair<TypeDefBase, LanguageLevelVisitor>(types[i], this));
    }
    for (int i = 0; i < types.length; i++) {
      String qualifiedClassName = getQualifiedClassName(types[i].getName().getText());
      if (_classesToBeParsed.containsKey(qualifiedClassName)) {
        types[i].visit(this);
      }
    }
    return forSourceFileOnly(that);
  }
}
