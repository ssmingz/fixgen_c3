class PlaceHold {
  public void testTopLevelInterfaceNameBeforeClassName()
      throws BadLocationException, ClassNameNotFoundException {
    String weird =
        ((("package edu . rice\n./*comment!*/cs.drjava; \n" + " interface thisInterface { \n")
                    + "  } \n")
                + " class thatClass {\n")
            + "  }";
    String result = "thisInterface";
    defModel.insertString(0, weird, null);
    assertEquals(
        ("interface should have been chosen, rather than the class: '" + weird) + "'",
        result,
        defModel.getFirstTopLevelClassName());
  }
}
