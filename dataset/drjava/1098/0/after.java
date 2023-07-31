class PlaceHold {
  public void setUp() {
    _sd1 = new SymbolData("i.like.monkey");
    errors = new LinkedList<Pair<String, JExpressionIF>>();
    LanguageLevelConverter.symbolTable = symbolTable = new Symboltable();
    visitedFiles = new LinkedList<Pair<LanguageLevelVisitor, SourceFile>>();
    _hierarchy = new Hashtable<String, TypeDefBase>();
    _cbiv =
        new ClassBodyIntermediateVisitor(
            _sd1,
            new File(""),
            "",
            new LinkedList<String>(),
            new LinkedList<String>(),
            new LinkedList<String>(),
            new Hashtable<String, Pair<SourceInfo, LanguageLevelVisitor>>());
    _cbiv.continuations = new Hashtable<String, Pair<SourceInfo, LanguageLevelVisitor>>();
    _cbiv._classesToBeParsed = new Hashtable<String, Pair<TypeDefBase, LanguageLevelVisitor>>();
    _cbiv._resetNonStaticFields();
    _cbiv._importedPackages.addFirst("java.lang");
    _errorAdded = false;
  }
}
