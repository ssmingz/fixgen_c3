class PlaceHold {
  public void testTopLevelInterfaceNameMisleading()
      throws BadLocationException, ClassNameNotFoundException {
    String weird =
        (("package edu . rice\n./*comment!*/cs.drjava; \n" + " {interface X} ") + " \"class Foo\"")
            + " class MyClass {";
    String result = "MyClass";
    defModel.insertString(0, weird, null);
    assertEquals(
        ("class name for interface: '" + weird) + "'",
        result,
        defModel.getFirstTopLevelClassName());
  }
}
