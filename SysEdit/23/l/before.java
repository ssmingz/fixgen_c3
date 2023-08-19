class PlaceHold {
  public RecoveredElement add(FieldDeclaration fieldDeclaration, int bracketBalance) {
    /* local variables inside method can only be final and non void */
    char[][] fieldTypeName;
    if ((((fieldDeclaration.modifiers & (~AccFinal)) != 0) // local var can only be final
            || (fieldDeclaration.type == null)) // initializer
        || (((fieldTypeName = fieldDeclaration.type.getTypeName()).length == 1) // non void
            && CharOperation.equals(fieldTypeName[0], VoidBinding.sourceName()))) {
      if (this.parent == null) {
        return this; // ignore

      } else {
        this.updateSourceEndIfNecessary(
            this.previousAvailableLineEnd(fieldDeclaration.declarationSourceStart - 1));
        return this.parent.add(fieldDeclaration, bracketBalance);
      }
    }
    /* default behavior is to delegate recording to parent if any,
    do not consider elements passed the known end (if set)
    it must be belonging to an enclosing element
     */
    if ((methodDeclaration.declarationSourceEnd > 0)
        && (fieldDeclaration.declarationSourceStart > methodDeclaration.declarationSourceEnd)) {
      if (this.parent == null) {
        return this; // ignore

      } else {
        return this.parent.add(fieldDeclaration, bracketBalance);
      }
    }
    /* consider that if the opening brace was not found, it is there */
    if (!foundOpeningBrace) {
      foundOpeningBrace = true;
      this.bracketBalance++;
    }
    // still inside method, treat as local variable
    return this; // ignore
  }
}
