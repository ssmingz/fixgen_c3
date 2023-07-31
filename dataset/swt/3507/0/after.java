class PlaceHold {
  private void calculateTabStops(String text, int tabWidth) {
    int tabIndex = text.indexOf('\t', 0);
    int logicalIndex = 0;
    int x = StyledText.XINSET;
    int spaceWidth = gc.stringExtent(" ").x;
    while (tabIndex != (-1)) {
      for (; logicalIndex < tabIndex; logicalIndex++) {
        x += dx[order[logicalIndex]];
      }
      int tabStop = x + tabWidth;
      if ((tabWidth - (tabStop % tabWidth)) < spaceWidth) {
        tabStop += tabWidth;
      }
      tabStop -= tabStop % tabWidth;
      dx[order[tabIndex]] = tabStop - x;
      tabIndex = text.indexOf('\t', tabIndex + 1);
    }
  }
}
