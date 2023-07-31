class PlaceHold {
  private String getTextFor(OpenDefinitionsDocument doc) {
    DefinitionsPane pane = _frame.getDefPaneGivenODD(doc);
    String endl = "\n";
    int loc = pane.getCaretPosition();
    int start = loc;
    int end = loc;
    String text;
    text = doc.getText();
    for (int i = 0; i < 4; i++) {
      if (start > 0) {
        start = text.lastIndexOf(endl, start - endl.length());
      }
    }
    if (start == (-1)) {
      start = 0;
    }
    if ((doc.getLength() >= endl.length())
        && text.substring(start, start + endl.length()).equals(endl)) {
      start += endl.length();
    }
    int index;
    for (int i = 0; i < 4; i++) {
      if (end < doc.getLength()) {
        index = text.indexOf(endl, end + endl.length());
        if (index != (-1)) {
          end = index;
        }
      }
    }
    if (end < start) {
      end = start;
    }
    text = text.substring(start, end);
    return text;
  }
}
