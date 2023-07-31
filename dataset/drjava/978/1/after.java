class PlaceHold {
  public Void forInnerInterfaceDef(InnerInterfaceDef cd) {
    String interfaceName = cd.getName().getText();
    if (_enclosingData == null) {
      throw new RuntimeException(
          "Internal Program Error: Enclosing Data is null.  Please report this bug.");
    }
    SymbolData sd = _enclosingData.getInnerClassOrInterface(interfaceName);
    if (sd == null) {
      throw new RuntimeException(
          ("Internal Program Error: Can't find SymbolData for " + cd.getName().getText())
              + ". Please report this bug.");
    }
    BracedBody bb = cd.getBody();
    sd.setAnonymousInnerClassNum(0);
    bb.visit(new Augmentor(sd));
    return null;
  }
}
