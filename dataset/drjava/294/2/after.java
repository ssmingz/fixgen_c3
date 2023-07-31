class PlaceHold {
  public final EnumDeclaration enumDeclaration() throws ParseException {
    EnumDeclaration ed;
    ModifierFlags md;
    md = modifiers();
    ed = unmodifiedEnumDeclaration(md);
    if (md != null) {
      checkModifiers(
          md.accessFlags,
          ((((Modifier.FINAL | Modifier.ABSTRACT) | Modifier.NATIVE) | Modifier.SYNCHRONIZED)
                  | Modifier.TRANSIENT)
              | Modifier.VOLATILE);
    }
    {
      if (true) {
        return ed;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
