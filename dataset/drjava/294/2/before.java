class PlaceHold {
  public final EnumDeclaration enumDeclaration() throws ParseException {
    EnumDeclaration ed;
    ModifierFlags md;
    md = modifiers();
    ed = unmodifiedEnumDeclaration(md);
    {
      if (true) {
        return ed;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
