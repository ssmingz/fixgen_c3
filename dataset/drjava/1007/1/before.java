class PlaceHold {
  public void forClassDef(ClassDef cd) {
    String className = cd.getName().getText();
    SymbolData sd = Augmentor._llv.symbolTable.get(_llv.getQualifiedClassName(className));
    if (sd == null) {
      throw new RuntimeException(
          ("Internal Program Error: Can't find SymbolData for " + cd.getName().getText())
              + " Please report this bug.");
    }
    ModifiersAndVisibility m = cd.getMav();
    if (_isElementaryFile() && sd.hasModifier("public")) {
      _readAndWriteThroughIndex(
          m.getSourceInfo().getStartLine(), m.getSourceInfo().getStartColumn() - 1);
      _writeToFileOut("public ");
    }
    BracedBody bb = cd.getBody();
    sd.setAnonymousInnerClassNum(0);
    bb.visit(new Augmentor(sd));
    int baseIndent = cd.getSourceInfo().getStartColumn() - 1;
    className = LanguageLevelVisitor.getUnqualifiedClassName(sd.getName());
    _readAndWriteThroughIndex(
        cd.getSourceInfo().getEndLine(), cd.getSourceInfo().getEndColumn() - 1);
    if (_isElementaryFile() || _isIntermediateFile()) {
      writeConstructor(className, sd, baseIndent);
      writeAccessors(sd, baseIndent);
      String valueToStringName = writeValueToString(sd, baseIndent);
      String valueEqualsName = writeValueEquals(sd, baseIndent);
      String valueHashCodeName = writeValueHashCode(sd, baseIndent, valueEqualsName);
      writeToString(sd, baseIndent, valueToStringName);
      writeEquals(className, sd, baseIndent, valueEqualsName);
      writeHashCode(className, sd, baseIndent, false, valueHashCodeName);
      for (String s : _endOfClassVarDefs) {
        _writeToFileOut((newLine + indentString(baseIndent, 1)) + s);
      }
      if (_endOfClassVarDefs.size() > 0) {
        _writeToFileOut(newLine);
        _endOfClassVarDefs.clear();
      }
      _writeToFileOut(indentString(baseIndent, 0));
    }
  }
}
