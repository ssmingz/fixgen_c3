class PlaceHold {
  void handleModify(ModifyEvent event) {
    if ((newCharCount > 0) && (start >= 0)) {
      StyleRange style = new StyleRange();
      if ((textFont != null) && (!textFont.equals(styledText.getFont()))) {
        style.font = textFont;
      } else {
        style.fontStyle = SWT.NONE;
        if (boldControl.getSelection()) {
          style.fontStyle |= SWT.BOLD;
        }
        if (italicControl.getSelection()) {
          style.fontStyle |= SWT.ITALIC;
        }
      }
      if ((styleState & FOREGROUND) != 0) {
        style.foreground = textForeground;
      }
      if ((styleState & BACKGROUND) != 0) {
        style.background = textBackground;
      }
      int underlineStyle = styleState & UNDERLINE;
      if (underlineStyle != 0) {
        style.underline = true;
        style.underlineColor = underlineColor;
        switch (underlineStyle) {
          case UNDERLINE_SINGLE:
            style.underlineStyle = SWT.UNDERLINE_SINGLE;
            break;
          case UNDERLINE_DOUBLE:
            style.underlineStyle = SWT.UNDERLINE_DOUBLE;
            break;
          case UNDERLINE_SQUIGGLE:
            style.underlineStyle = SWT.UNDERLINE_SQUIGGLE;
            break;
          case UNDERLINE_ERROR:
            style.underlineStyle = SWT.UNDERLINE_ERROR;
            break;
          case UNDERLINE_LINK:
            {
              style.underlineColor = null;
              if ((link != null) && (link.length() > 0)) {
                style.underlineStyle = SWT.UNDERLINE_LINK;
                style.data = link;
              } else {
                style.underline = false;
              }
              break;
            }
        }
      }
      if ((styleState & STRIKEOUT) != 0) {
        style.strikeout = true;
        style.strikeoutColor = strikeoutColor;
      }
      int borderStyle = styleState & BORDER;
      if (borderStyle != 0) {
        style.borderColor = borderColor;
        switch (borderStyle) {
          case BORDER_DASH:
            style.borderStyle = SWT.BORDER_DASH;
            break;
          case BORDER_DOT:
            style.borderStyle = SWT.BORDER_DOT;
            break;
          case BORDER_SOLID:
            style.borderStyle = SWT.BORDER_SOLID;
            break;
        }
      }
      int[] ranges = new int[] {start, newCharCount};
      StyleRange[] styles = new StyleRange[] {style};
      styledText.setStyleRanges(start, newCharCount, ranges, styles);
    }
    disposeRanges(selectedRanges);
  }
}
