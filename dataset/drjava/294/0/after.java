class PlaceHold {
  public final InterfaceDeclaration interfaceDeclaration() throws ParseException {
    InterfaceDeclaration id;
    ModifierFlags md;
    md = modifiers();
    id = unmodifiedInterfaceDeclaration(md);
    if (md != null) {
      checkModifiers(
          md.accessFlags,
          (((Modifier.FINAL | Modifier.NATIVE) | Modifier.SYNCHRONIZED) | Modifier.TRANSIENT)
              | Modifier.VOLATILE);
    }
    {
      if (true) {
        return id;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
