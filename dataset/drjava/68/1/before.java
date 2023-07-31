class PlaceHold {
  public void setUp() {
    _sd1 = new SymbolData("i.like.monkey");
    _sd2 = new SymbolData("i.like.giraffe");
    _sd3 = new SymbolData("zebra");
    _sd4 = new SymbolData("u.like.emu");
    _sd5 = new SymbolData("");
    _sd6 = new SymbolData("cebu");
    _lvtc =
        new LValueTypeChecker(
            new Bob(
                _sd1,
                new File(""),
                "",
                new LinkedList<String>(),
                new LinkedList<String>(),
                new LinkedList<VariableData>(),
                new LinkedList<Pair<SymbolData, JExpression>>()));
    _ta = _lvtc._testAssignableInstance;
    _lvtc._bob.errors = new LinkedList<Pair<String, JExpressionIF>>();
    _lvtc._bob.symbolTable = new Symboltable();
    LanguageLevelVisitor.symbolTable = _lvtc._bob.symbolTable;
    _lvtc._bob._targetVersion = "1.5";
    _lvtc._bob._importedPackages.addFirst("java.lang");
  }
}
