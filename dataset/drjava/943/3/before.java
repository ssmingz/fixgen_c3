class PlaceHold {
  public void setUp() {
    errors = new LinkedList<Pair<String, JExpressionIF>>();
    symbolTable = new Symboltable();
    LanguageLevelVisitor.symbolTable = symbolTable;
    _btc = new TypeChecker(new File(""), "", new LinkedList<String>(), new LinkedList<String>());
    _btc._targetVersion = "version 1.5";
    _btc._importedPackages.addFirst("java.lang");
    _errorAdded = false;
    _sd1 = new SymbolData("i.like.monkey");
    _sd2 = new SymbolData("i.like.giraffe");
    _sd3 = new SymbolData("zebra");
    _sd4 = new SymbolData("u.like.emu");
    _sd5 = new SymbolData("");
    _sd6 = new SymbolData("cebu");
    SymbolData Double = new SymbolData("java.lang.Double");
    SymbolData Float = new SymbolData("java.lang.Float");
    SymbolData Long = new SymbolData("java.lang.Long");
    SymbolData Integer = new SymbolData("java.lang.Integer");
    SymbolData o = new SymbolData("java.lang.Object");
    o.setIsContinuation(false);
    o.setMav(_publicMav);
    symbolTable.put("java.lang.Object", o);
    Integer.setSuperClass(o);
    SymbolData Short = new SymbolData("java.lang.Short");
    SymbolData Character = new SymbolData("java.lang.Character");
    SymbolData Byte = new SymbolData("java.lang.Byte");
    SymbolData Boolean = new SymbolData("java.lang.Boolean");
    symbolTable.put("java.lang.Double", Double);
    symbolTable.put("java.lang.Float", Float);
    symbolTable.put("java.lang.Long", Long);
    symbolTable.put("java.lang.Integer", Integer);
    symbolTable.put("java.lang.Short", Short);
    symbolTable.put("java.lang.Character", Character);
    symbolTable.put("java.lang.Byte", Byte);
    symbolTable.put("java.lang.Boolean", Boolean);
  }
}
