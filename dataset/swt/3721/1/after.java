class PlaceHold {
  int setAttributes(int startOffset, int endOffset, long pbstrAttributes) {
    if (DEBUG) {
      print(
          (((((this + ".IAccessibleEditableText::setAttributes, start=") + startOffset) + ", end=")
                      + endOffset)
                  + ", pbstrAttributes=")
              + pbstrAttributes);
    }
    AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
    String string = getString(pbstrAttributes);
    if ((string != null) && (string.length() > 0)) {
      event.start = (startOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : startOffset;
      event.end = (endOffset == COM.IA2_TEXT_OFFSET_LENGTH) ? getCharacterCount() : endOffset;
      TextStyle style = new TextStyle();
      FontData fontData = null;
      int points = 10;
      String[] attributes = new String[0];
      int begin = 0;
      int end = string.indexOf(';');
      while ((end != (-1)) && (end < string.length())) {
        String keyValue = string.substring(begin, end).trim();
        int colonIndex = keyValue.indexOf(':');
        if ((colonIndex != (-1)) && ((colonIndex + 1) < keyValue.length())) {
          String[] newAttributes = new String[attributes.length + 2];
          System.arraycopy(attributes, 0, newAttributes, 0, attributes.length);
          newAttributes[attributes.length] = keyValue.substring(0, colonIndex).trim();
          newAttributes[attributes.length + 1] = keyValue.substring(colonIndex + 1).trim();
          attributes = newAttributes;
        }
        begin = end + 1;
        end = string.indexOf(';', begin);
      }
      for (int i = 0; (i + 1) < attributes.length; i += 2) {
        String key = attributes[i];
        String value = attributes[i + 1];
        if (key.equals("text-position")) {
          if (value.equals("super")) {
            style.rise = points / 2;
          } else if (value.equals("sub")) {
            style.rise = (-points) / 2;
          }
        } else if (key.equals("text-underline-type")) {
          style.underline = true;
          if (value.equals("double")) {
            style.underlineStyle = SWT.UNDERLINE_DOUBLE;
          } else if (value.equals("single")) {
            if ((style.underlineStyle != SWT.UNDERLINE_SQUIGGLE)
                && (style.underlineStyle != SWT.UNDERLINE_ERROR)) {
              style.underlineStyle = SWT.UNDERLINE_SINGLE;
            }
          }
        } else if (key.equals("text-underline-style") && value.equals("wave")) {
          style.underline = true;
          style.underlineStyle = SWT.UNDERLINE_SQUIGGLE;
        } else if (key.equals("invalid") && value.equals("true")) {
          style.underline = true;
          style.underlineStyle = SWT.UNDERLINE_ERROR;
        } else if (key.equals("text-line-through-type")) {
          if (value.equals("single")) {
            style.strikeout = true;
          }
        } else if (key.equals("font-family")) {
          if (fontData == null) {
            fontData = new FontData();
          }
          fontData.setName(value);
        } else if (key.equals("font-size")) {
          try {
            String pts = (value.endsWith("pt")) ? value.substring(0, value.length() - 2) : value;
            points = Integer.parseInt(pts);
            if (fontData == null) {
              fontData = new FontData();
            }
            fontData.setHeight(points);
            if (style.rise > 0) {
              style.rise = points / 2;
            } else if (style.rise < 0) {
              style.rise = (-points) / 2;
            }
          } catch (NumberFormatException ex) {
          }
        } else if (key.equals("font-style")) {
          if (value.equals("italic")) {
            if (fontData == null) {
              fontData = new FontData();
            }
            fontData.setStyle(fontData.getStyle() | SWT.ITALIC);
          }
        } else if (key.equals("font-weight")) {
          if (value.equals("bold")) {
            if (fontData == null) {
              fontData = new FontData();
            }
            fontData.setStyle(fontData.getStyle() | SWT.BOLD);
          } else {
            try {
              int weight = Integer.parseInt(value);
              if (fontData == null) {
                fontData = new FontData();
              }
              if (weight > 400) {
                fontData.setStyle(fontData.getStyle() | SWT.BOLD);
              }
            } catch (NumberFormatException ex) {
            }
          }
        } else if (key.equals("color")) {
          style.foreground = colorFromString(value);
        } else if (key.equals("background-color")) {
          style.background = colorFromString(value);
        }
      }
      if (attributes.length > 0) {
        event.attributes = attributes;
        if (fontData != null) {
          style.font = new Font(control.getDisplay(), fontData);
        }
        if (!style.equals(new TextStyle())) {
          event.textStyle = style;
        }
      }
      for (int i = 0; i < accessibleEditableTextListenersSize(); i++) {
        AccessibleEditableTextListener listener = accessibleEditableTextListeners.get(i);
        listener.setTextAttributes(event);
      }
      if (style.font != null) {
        style.font.dispose();
      }
      if (style.foreground != null) {
        style.foreground.dispose();
      }
      if (style.background != null) {
        style.background.dispose();
      }
    }
    if ((event.result == null) || (!event.result.equals(OK))) {
      return COM.E_INVALIDARG;
    }
    return COM.S_OK;
  }
}
