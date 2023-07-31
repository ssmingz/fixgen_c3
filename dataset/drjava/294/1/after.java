class PlaceHold {
  public final ClassDeclaration classDeclaration() throws ParseException {
    ClassDeclaration cd;
    ModifierFlags md;
    md = modifiers();
    cd = unmodifiedClassDeclaration(md);
    if (md != null) {
      checkModifiers(
          md.accessFlags,
          ((Modifier.NATIVE | Modifier.SYNCHRONIZED) | Modifier.TRANSIENT) | Modifier.VOLATILE);
    }
    {
      if (true) {
        return cd;
      }
    }
    throw new Error("Missing return statement in function");
  }
}
