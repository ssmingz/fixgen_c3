class PlaceHold {
  public void testClassNameMisleading() throws BadLocationException {
    String weird =
        (("package edu . rice\n./*comment!*/cs.drjava; \n" + " {class X} \n")
                + " interface thisInterface { \n")
            + " class MyInnerClass {";
    String result = "thisInterface";
    defModel.insertString(0, weird, null);
    assertEquals(("class name for interface: '" + weird) + "'", result, defModel.getClassName());
  }
}
