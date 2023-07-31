class PlaceHold {
  public final InterfaceDeclaration interfaceDeclaration() throws ParseException {
    InterfaceDeclaration id;
    ModifierFlags md;
    md = modifiers();
    id = unmodifiedInterfaceDeclaration(md);
    {
      if (true) {
        return id;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
