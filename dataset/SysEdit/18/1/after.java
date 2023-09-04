class PlaceHold {
  public void userInput(char ch) {
    if (!isEditable()) {
      getToolkit().beep();
      return;
    }
    /* Null before addNotify() */
    if (hiddenCursor != null) getPainter().setCursor(hiddenCursor);

    if (ch == '\t') userInputTab();
    else {
      boolean indent = buffer.isElectricKey(ch, caretLine);
      String str = String.valueOf(ch);
      if (getSelectionCount() == 0) {
        if (!doWordWrap(ch == ' ')) insert(str, indent);

      } else replaceSelection(str);
    }
  }
}
