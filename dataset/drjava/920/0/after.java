class PlaceHold {
  public void testUndoAndRedoAfterMultipleLineCommentAndUncomment() throws BadLocationException {
    String text =
        ((((("public class stuff {\n" + "  private int _int;\n") + "  private Bar _bar;\n")
                        + "  public void foo() {\n")
                    + "    _bar.baz(_int);\n")
                + "  }\n")
            + "}\n";
    String commented =
        ((((("//public class stuff {\n" + "//  private int _int;\n") + "//  private Bar _bar;\n")
                        + "//  public void foo() {\n")
                    + "//    _bar.baz(_int);\n")
                + "//  }\n")
            + "//}\n";
    _defModel.addUndoableEditListener(_defModel.getUndoManager());
    DrJava.getConfig().setSetting(INDENT_LEVEL, Integer.valueOf(2));
    _defModel.insertString(0, text, null);
    assertEquals("insertion", text, _defModel.getText());
    _defModel.getUndoManager().startCompoundEdit();
    _defModel.commentLines(0, _defModel.getLength());
    assertEquals("commenting", commented, _defModel.getText());
    _defModel.getUndoManager().undo();
    assertEquals("undo commenting", text, _defModel.getText());
    _defModel.getUndoManager().redo();
    assertEquals("redo commenting", commented, _defModel.getText());
    _defModel.getUndoManager().startCompoundEdit();
    _defModel.uncommentLines(0, _defModel.getLength());
    assertEquals("uncommenting", text, _defModel.getText());
    _defModel.getUndoManager().undo();
    assertEquals("undo uncommenting", commented, _defModel.getText());
    _defModel.getUndoManager().redo();
    assertEquals("redo uncommenting", text, _defModel.getText());
  }
}
