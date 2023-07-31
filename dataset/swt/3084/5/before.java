class PlaceHold {
  int get_attributes(int offset, long pStartOffset, long pEndOffset, long pbstrTextAttributes) {
    AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
    event.offset = (offset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : offset;
    for (int i = 0; i < accessibleAttributeListenersSize(); i++) {
      AccessibleAttributeListener listener =
          ((AccessibleAttributeListener) (accessibleAttributeListeners.elementAt(i)));
      listener.getTextAttributes(event);
    }
    String textAttributes = "";
    TextStyle style = event.textStyle;
    if (style != null) {
      if (style.rise != 0) {
        textAttributes += "text-position:";
        if (style.rise > 0) {
          textAttributes += "super";
        } else {
          textAttributes += "sub";
        }
      }
      if (style.underline) {
        textAttributes += "text-underline-type:";
        switch (style.underlineStyle) {
          case SWT.UNDERLINE_SINGLE:
            textAttributes += "single;";
            break;
          case SWT.UNDERLINE_DOUBLE:
            textAttributes += "double;";
            break;
          case SWT.UNDERLINE_SQUIGGLE:
            textAttributes += "single;text-underline-style:wave;";
            break;
          case SWT.UNDERLINE_ERROR:
            textAttributes += "single;text-underline-style:wave;invalid:true;";
            break;
          default:
            textAttributes += "none;";
            break;
        }
      }
      if (style.strikeout) {
        textAttributes += "text-line-through-type:single;";
      }
      Font font = style.font;
      if ((font != null) && (!font.isDisposed())) {
        FontData fontData = font.getFontData()[0];
        textAttributes += ("font-family:" + fontData.getName()) + ";";
        textAttributes += ("font-size:" + fontData.getHeight()) + "pt;";
        textAttributes +=
            ("font-style:" + (fontData.data.lfItalic != 0 ? "italic" : "normal")) + ";";
        textAttributes += ("font-weight:" + fontData.data.lfWeight) + ";";
      }
      Color color = style.foreground;
      if ((color != null) && (!color.isDisposed())) {
        textAttributes +=
            ((((("color:rgb(" + color.getRed()) + ",") + color.getGreen()) + ",") + color.getBlue())
                + ");";
      }
      color = style.background;
      if ((color != null) && (!color.isDisposed())) {
        textAttributes +=
            ((((("background-color:rgb(" + color.getRed()) + ",") + color.getGreen()) + ",")
                    + color.getBlue())
                + ");";
      }
    }
    if (event.attributes != null) {
      for (int i = 0; (i + 1) < event.attributes.length; i += 2) {
        textAttributes += ((event.attributes[i] + ":") + event.attributes[i + 1]) + ";";
      }
    }
    if (DEBUG) {
      print(
          (((((((this + ".IAccessibleText::get_attributes(") + offset) + ") returning start = ")
                              + event.start)
                          + ", end = ")
                      + event.end)
                  + ", attributes = ")
              + textAttributes);
    }
    COM.MoveMemory(pStartOffset, new int[] {event.start}, 4);
    COM.MoveMemory(pEndOffset, new int[] {event.end}, 4);
    setString(pbstrTextAttributes, textAttributes);
    if (textAttributes.length() == 0) {
      return COM.S_FALSE;
    }
    return COM.S_OK;
  }
}
