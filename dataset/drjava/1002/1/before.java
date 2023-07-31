class PlaceHold {
  public void setUp() {
    errors = new LinkedList<Pair<String, JExpressionIF>>();
    symbolTable = new Symboltable();
    _etc =
        new ExpressionTypeChecker(
            null,
            new File(""),
            "",
            new LinkedList<String>(),
            new LinkedList<String>(),
            new LinkedList<VariableData>(),
            new LinkedList<Pair<SymbolData, JExpression>>());
    _etc._targetVersion = JavaVersion.JAVA_5;
    _etc._importedPackages.addFirst("java.lang");
    _sd1 = new SymbolData("i.like.monkey");
    _sd2 = new SymbolData("i.like.giraffe");
    _sd3 = new SymbolData("zebra");
    _sd4 = new SymbolData("u.like.emu");
    _sd5 = new SymbolData("");
    _sd6 = new SymbolData("cebu");
    _etc._data = _sd1;
  }
}
