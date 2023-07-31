class PlaceHold {
  public void indentLines(int selStart, int selEnd) {
    DefinitionsDocument doc = getDocument();
    doc.indentLines(selStart, selEnd);
  }
}
