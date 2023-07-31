class PlaceHold {
  private void displayBinary(BinaryExpression be) {
    print(((("l." + be.getBeginLine()) + " ") + be.getClass().getName()) + " {");
    print("leftExpression:");
    indent();
    be.getLeftExpression().acceptVisitor(this);
    unindent();
    print("rightExpression:");
    indent();
    be.getRightExpression().acceptVisitor(this);
    unindent();
    displayProperties(be);
    print("}");
  }
}
