class PlaceHold {
  protected VariableData[] formalParameters2VariableData(FormalParameter[] fps, Data d) {
    VariableData[] varData = new VariableData[fps.length];
    VariableDeclarator vd;
    String[] mav;
    if ((d instanceof MethodData) && d.hasModifier("static")) {
      mav = new String[] {"final", "static"};
    } else {
      mav = new String[] {"final"};
    }
    for (int i = 0; i < varData.length; i++) {
      vd = fps[i].getDeclarator();
      String name = vd.getName().getText();
      SymbolData type = getSymbolData(vd.getType().getName(), vd.getType().getSourceInfo());
      if (type == null) {
        type = d.getInnerClassOrInterface(vd.getType().getName());
      }
      if (type == null) {
        String typeName = (d.getSymbolData().getName() + ".") + vd.getType().getName();
        type = new SymbolData(typeName);
        d.getSymbolData().addInnerClass(type);
        type.setOuterData(d.getSymbolData());
        continuations.put(
            typeName,
            new Pair<SourceInfo, LanguageLevelVisitor>(vd.getType().getSourceInfo(), this));
      }
      varData[i] =
          new VariableData(
              name, new ModifiersAndVisibility(SourceInfo.NO_INFO, mav), type, true, d);
      varData[i].gotValue();
    }
    return varData;
  }
}
