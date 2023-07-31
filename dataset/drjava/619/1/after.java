class PlaceHold {
  public void modifyText(ModifyEvent e) {
    StyledText pane = _view.getTextPane();
    int caretPos = pane.getCaretOffset();
    int promptPos = _doc.getPromptPos();
    int docLength = _doc.getLength();
    if (_doc.inProgress()) {
      moveToEnd();
    } else if ((caretPos < promptPos) && (promptPos <= docLength)) {
      moveToPrompt();
    } else {
      pane.showSelection();
    }
  }
}
