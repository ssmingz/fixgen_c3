class PlaceHold {
  void updateToolBar() {
    styleState = 0;
    link = null;
    boolean bold = false;
    boolean italic = false;
    Font font = null;
    int offset = styledText.getCaretOffset();
    StyleRange range = (offset > 0) ? styledText.getStyleRangeAtOffset(offset - 1) : null;
    if (range != null) {
      if (range.font != null) {
        font = range.font;
        FontData[] fds = font.getFontData();
        for (int i = 0; i < fds.length; i++) {
          int fontStyle = fds[i].getStyle();
          if ((!bold) && ((fontStyle & SWT.BOLD) != 0)) {
            bold = true;
          }
          if ((!italic) && ((fontStyle & SWT.ITALIC) != 0)) {
            italic = true;
          }
        }
      } else {
        bold = (range.fontStyle & SWT.BOLD) != 0;
        italic = (range.fontStyle & SWT.ITALIC) != 0;
      }
      if (range.foreground != null) {
        styleState |= TEXT_FOREGROUND;
        if (textForeground != range.foreground) {
          disposeResource(textForeground);
          textForeground = range.foreground;
        }
      }
      if (range.background != null) {
        styleState |= TEXT_FOREGROUND;
        if (textBackground != range.background) {
          disposeResource(textBackground);
          textBackground = range.background;
        }
      }
      if (range.underline) {
        switch (range.underlineStyle) {
          case SWT.UNDERLINE_SINGLE:
            styleState |= UNDERLINE_SINGLE;
            break;
          case SWT.UNDERLINE_DOUBLE:
            styleState |= UNDERLINE_DOUBLE;
            break;
          case SWT.UNDERLINE_SQUIGGLE:
            styleState |= UNDERLINE_SQUIGGLE;
            break;
          case SWT.UNDERLINE_ERROR:
            styleState |= UNDERLINE_ERROR;
            break;
          case SWT.UNDERLINE_LINK:
            styleState |= UNDERLINE_LINK;
            link = ((String) (range.data));
            break;
        }
        if (range.underlineStyle != SWT.UNDERLINE_LINK) {
          underlineSingleItem.setSelection((styleState & UNDERLINE_SINGLE) != 0);
          underlineDoubleItem.setSelection((styleState & UNDERLINE_DOUBLE) != 0);
          underlineErrorItem.setSelection((styleState & UNDERLINE_ERROR) != 0);
          underlineSquiggleItem.setSelection((styleState & UNDERLINE_SQUIGGLE) != 0);
          disposeResource(underlineColor);
          underlineColor = range.underlineColor;
        }
      }
      if (range.strikeout) {
        styleState |= STRIKEOUT;
        disposeResource(strikeoutColor);
        strikeoutColor = range.strikeoutColor;
      }
      if (range.borderStyle != SWT.NONE) {
        switch (range.borderStyle) {
          case SWT.BORDER_SOLID:
            styleState |= BORDER_SOLID;
            break;
          case SWT.BORDER_DASH:
            styleState |= BORDER_DASH;
            break;
          case SWT.BORDER_DOT:
            styleState |= BORDER_DOT;
            break;
        }
        borderSolidItem.setSelection((styleState & BORDER_SOLID) != 0);
        borderDashItem.setSelection((styleState & BORDER_DASH) != 0);
        borderDotItem.setSelection((styleState & BORDER_DOT) != 0);
        disposeResource(borderColor);
        borderColor = range.borderColor;
      }
    }
    boldControl.setSelection(bold);
    italicControl.setSelection(italic);
    FontData fontData =
        (font != null) ? font.getFontData()[0] : styledText.getFont().getFontData()[0];
    int index = 0;
    int count = fontNameControl.getItemCount();
    String fontName = fontData.getName();
    while (index < count) {
      if (fontNameControl.getItem(index).equals(fontName)) {
        fontNameControl.select(index);
        break;
      }
      index++;
    }
    index = 0;
    count = fontSizeControl.getItemCount();
    int fontSize = fontData.getHeight();
    while (index < count) {
      int size = Integer.parseInt(fontSizeControl.getItem(index));
      if (fontSize == size) {
        fontSizeControl.select(index);
        break;
      }
      if (size > fontSize) {
        fontSizeControl.add(String.valueOf(fontSize), index);
        fontSizeControl.select(index);
        break;
      }
      index++;
    }
    disposeResource(textFont);
    textFont = font;
    int lineIndex = styledText.getLineAtOffset(offset);
    int alignment = styledText.getLineAlignment(lineIndex);
    leftAlignmentItem.setSelection((alignment & SWT.LEFT) != 0);
    centerAlignmentItem.setSelection((alignment & SWT.CENTER) != 0);
    rightAlignmentItem.setSelection((alignment & SWT.RIGHT) != 0);
    boolean justify = styledText.getLineJustify(lineIndex);
    justifyAlignmentItem.setSelection(justify);
  }
}
