class PlaceHold {
  public RecoveredElement add(FieldDeclaration newFieldDeclaration, int bracketBalanceValue) {
    /* local variables inside initializer can only be final and non void */
    char[][] fieldTypeName;
    if ((((newFieldDeclaration.modifiers & (~AccFinal)) != 0) /* local var can only be final */
            || (newFieldDeclaration.type == null)) // initializer
        || (((fieldTypeName = newFieldDeclaration.type.getTypeName()).length == 1) // non void
            && CharOperation.equals(fieldTypeName[0], VoidBinding.sourceName()))) {
      if (this.parent == null) {
        return this; // ignore

      } else {
        this.updateSourceEndIfNecessary(
            this.previousAvailableLineEnd(newFieldDeclaration.declarationSourceStart - 1));
        return this.parent.add(newFieldDeclaration, bracketBalanceValue);
      }
    }
    /* default behavior is to delegate recording to parent if any,
    do not consider elements passed the known end (if set)
    it must be belonging to an enclosing element
     */
    if ((this.fieldDeclaration.declarationSourceEnd > 0)
        && (newFieldDeclaration.declarationSourceStart
            > this.fieldDeclaration.declarationSourceEnd)) {
      if (this.parent == null) {
        return this; // ignore

      } else {
        return this.parent.add(newFieldDeclaration, bracketBalanceValue);
      }
    }
    // still inside initializer, treat as local variable
    return this; // ignore
  }
}
