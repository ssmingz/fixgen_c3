class PlaceHold {
  public void setUp() {
    _sd1 = new SymbolData("i.like.monkey");
    _sd2 = new SymbolData("i.like.giraffe");
    _sd3 = new SymbolData("zebra");
    _sd4 = new SymbolData("u.like.emu");
    _sd5 = new SymbolData("");
    _sd6 = new SymbolData("cebu");
    _bd1 =
        new MethodData(
            "monkey",
            _packageMav,
            new TypeParameter[0],
            _sd1,
            new VariableData[] {
              new VariableData("i", _publicMav, SymbolData.INT_TYPE, true, null),
              new VariableData(SymbolData.BOOLEAN_TYPE)
            },
            new String[0],
            _sd1,
            null);
    ((MethodData) (_bd1)).getParams()[0].setEnclosingData(_bd1);
    ((MethodData) (_bd1)).getParams()[1].setEnclosingData(_bd1);
    errors = new LinkedList<Pair<String, JExpressionIF>>();
    symbolTable = new Symboltable();
    _bd1.addEnclosingData(_sd1);
    _bd1.addFinalVars(((MethodData) (_bd1)).getParams());
    _tcbtc =
        new TryCatchBodyTypeChecker(
            _bd1,
            new File(""),
            "",
            new LinkedList<String>(),
            new LinkedList<String>(),
            new LinkedList<VariableData>(),
            new LinkedList<Pair<SymbolData, JExpression>>());
    LanguageLevelConverter.OPT = new Options(JavaVersion.JAVA_5, IterUtil.<File>empty());
    _tcbtc._importedPackages.addFirst("java.lang");
  }
}
