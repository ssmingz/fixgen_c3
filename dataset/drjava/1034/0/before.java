class PlaceHold {
  public void setUp() {
    _sd1 = new SymbolData("i.like.monkey");
    _sd2 = new SymbolData("i.like.giraffe");
    _sd3 = new SymbolData("zebra");
    _sd4 = new SymbolData("u.like.emu");
    _sd5 = new SymbolData("");
    _sd6 = new SymbolData("cebu");
    errors = new LinkedList<Pair<String, JExpressionIF>>();
    symbolTable = new Symboltable();
    _cbbtc =
        new ClassBodyTypeChecker(
            _sd1,
            new File(""),
            "",
            new LinkedList<String>(),
            new LinkedList<String>(),
            new LinkedList<VariableData>(),
            new LinkedList<Pair<SymbolData, JExpression>>());
    _cbbtc._targetVersion = JavaVersion.JAVA_5;
    _cbbtc._importedPackages.addFirst("java.lang");
  }
}
