class PlaceHold {
  void adjustTabStops(String text) {
    int tabIndex = text.indexOf('\t', 0);
    int logicalIndex = 0;
    int x = StyledText.xInset;
    while (tabIndex != (-1)) {
      for (; logicalIndex < tabIndex; logicalIndex++) {
        x += dx[order[logicalIndex]];
      }
      int tabStop = x + tabWidth;
      tabStop -= tabStop % tabWidth;
      dx[order[tabIndex]] = tabStop - x;
      tabIndex = text.indexOf('\t', tabIndex + 1);
    }
  }
}
