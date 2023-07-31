class PlaceHold {
  protected VariableData[] formalParameters2VariableData(FormalParameter[] fps, Data enclosing) {
    VariableData[] varData = new VariableData[fps.length];
    VariableDeclarator vd;
    String[] mav = new String[] {"final"};
    for (int i = 0; i < varData.length; i++) {
      vd = fps[i].getDeclarator();
      String name = vd.getName().getText();
      String typeName = vd.getType().getName();
      SourceInfo si = vd.getType().getSourceInfo();
      SymbolData type = getSymbolData(typeName, si);
      if (type == null) {
        type = enclosing.getInnerClassOrInterface(typeName);
      }
      if (type == null) {
        String qualifiedTypeName = (enclosing.getSymbolData().getName() + ".") + typeName;
        if (_innerClassesToBeParsed.contains(qualifiedTypeName)) {
          type = addInnerSymbolData(si, qualifiedTypeName, enclosing);
        } else {
          type = addSymbolData(si, typeName);
        }
      }
      varData[i] =
          new VariableData(
              name, new ModifiersAndVisibility(SourceInfo.NO_INFO, mav), type, true, enclosing);
      varData[i].gotValue();
    }
    return varData;
  }
}
