class PlaceHold {
  public void setText(int index, String text) {
    checkWidget();
    int columnCount = Math.max(parent.getTable().getColumnCount(), 1);
    if ((index < 0) || (index >= columnCount)) {
      return;
    }
    if (texts.length < columnCount) {
      String[] newTexts = new String[columnCount];
      System.arraycopy(texts, 0, newTexts, 0, texts.length);
      texts = newTexts;
    }
    texts[index] = text;
    if (tableItem != null) {
      tableItem.setText(index, text);
    }
  }
}
