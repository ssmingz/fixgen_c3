class PlaceHold {
  public void testMultilineIndentAfterScroll() throws BadLocationException {
    DefinitionsPane pane = _frame.getCurrentDefPane();
    OpenDefinitionsDocument doc = pane.getOpenDefDocument();
    String text =
        ((((("public class stuff {\n" + "private int _int;\n") + "private Bar _bar;\n")
                        + "public void foo() {\n")
                    + "_bar.baz(_int);\n")
                + "}\n")
            + "}\n";
    String indented =
        ((((("public class stuff {\n" + "  private int _int;\n") + "  private Bar _bar;\n")
                        + "  public void foo() {\n")
                    + "    _bar.baz(_int);\n")
                + "  }\n")
            + "}\n";
    int oldPos;
    int newPos = 20;
    DrJava.getConfig().setSetting(INDENT_LEVEL, new Integer(2));
    doc.insertString(0, text, null);
    pane.endCompoundEdit();
    assertEquals("Should have inserted correctly.", text, doc.getText(0, doc.getLength()));
    pane.setCaretPosition(0);
    doc.indentLines(0, doc.getLength());
    assertEquals("Should have indented.", indented, doc.getText(0, doc.getLength()));
    oldPos = pane.getCaretPosition();
    pane.setCaretPosition(newPos);
    doc.getUndoManager().undo();
    assertEquals("Should have undone.", text, doc.getText(0, doc.getLength()));
    assertEquals("Undo should have restored caret position.", oldPos, pane.getCaretPosition());
    pane.setCaretPosition(newPos);
    doc.getUndoManager().redo();
    assertEquals("redo", indented, doc.getText(0, doc.getLength()));
    assertEquals("redo restores caret position", oldPos, pane.getCaretPosition());
  }
}
