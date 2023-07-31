class PlaceHold {
  id getAttributedStringForRangeParameterizedAttribute(id parameter, int childID) {
    if (accessibleAttributeListeners.size() == 0) {
      return null;
    }
    id stringFragment = getStringForRangeParameterizedAttribute(parameter, childID);
    NSMutableAttributedString attribString =
        ((NSMutableAttributedString) (new NSMutableAttributedString().alloc()));
    attribString.initWithString(new NSString(stringFragment), null);
    attribString.autorelease();
    NSValue parameterObject = new NSValue(parameter.id);
    NSRange range = parameterObject.rangeValue();
    AccessibleTextAttributeEvent event = new AccessibleTextAttributeEvent(this);
    event.offset = ((int) (range.location));
    event.start = event.end = -1;
    NSRange attributeRange = new NSRange();
    while (event.offset < (range.location + range.length)) {
      if (accessibleAttributeListeners.size() > 0) {
        for (int i = 0; i < accessibleAttributeListeners.size(); i++) {
          AccessibleAttributeListener listener =
              ((AccessibleAttributeListener) (accessibleAttributeListeners.elementAt(i)));
          listener.getTextAttributes(event);
        }
      }
      if ((event.start == (-1)) && (event.end == (-1))) {
        return stringFragment;
      }
      attributeRange.location = event.start - range.location;
      attributeRange.length = event.end - event.start;
      if (attributeRange.location < 0) {
        attributeRange.length -= -attributeRange.location;
        attributeRange.location = 0;
      }
      if ((attributeRange.location + attributeRange.length) > range.length) {
        attributeRange.length = range.length - attributeRange.location;
      }
      event.offset = event.end;
      if (event.textStyle != null) {
        TextStyle ts = event.textStyle;
        if (ts.font != null) {
          NSMutableDictionary fontInfoDict = NSMutableDictionary.dictionaryWithCapacity(4);
          NSFont fontUsed = ts.font.handle;
          NSString fontName = fontUsed.fontName();
          fontInfoDict.setValue(fontName, NSAccessibilityFontNameKey);
          NSString familyName = fontUsed.familyName();
          fontInfoDict.setValue(familyName, NSAccessibilityFontFamilyKey);
          NSString displayName = fontUsed.displayName();
          fontInfoDict.setValue(displayName, NSAccessibilityVisibleNameKey);
          float fontSize = fontUsed.pointSize();
          fontInfoDict.setValue(NSNumber.numberWithDouble(fontSize), NSAccessibilityFontSizeKey);
          attribString.addAttribute(NSAccessibilityFontTextAttribute, fontInfoDict, attributeRange);
        }
        if (ts.foreground != null) {
          addCGColor(
              ts.foreground.handle,
              attribString,
              NSAccessibilityForegroundColorTextAttribute,
              attributeRange);
        }
        if (ts.background != null) {
          addCGColor(
              ts.background.handle,
              attribString,
              NSAccessibilityBackgroundColorTextAttribute,
              attributeRange);
        }
        if (ts.underline) {
          int style = ts.underlineStyle;
          NSString attribute = OS.NSAccessibilityUnderlineTextAttribute;
          NSNumber styleObj = null;
          switch (style) {
            case SWT.UNDERLINE_SINGLE:
              styleObj = NSNumber.numberWithInt(kAXUnderlineStyleSingle);
              break;
            case SWT.UNDERLINE_DOUBLE:
              styleObj = NSNumber.numberWithInt(kAXUnderlineStyleDouble);
              break;
            case SWT.UNDERLINE_SQUIGGLE:
              attribute = OS.NSAccessibilityMisspelledTextAttribute;
              styleObj = NSNumber.numberWithBool(true);
              break;
            default:
              styleObj = NSNumber.numberWithInt(kAXUnderlineStyleNone);
          }
          attribString.addAttribute(attribute, styleObj, attributeRange);
        }
        if (ts.underlineColor != null) {
          addCGColor(
              ts.underlineColor.handle,
              attribString,
              NSAccessibilityUnderlineColorTextAttribute,
              attributeRange);
        }
        if (ts.strikeout) {
          attribString.addAttribute(
              NSAccessibilityStrikethroughTextAttribute,
              NSNumber.numberWithBool(true),
              attributeRange);
          if (ts.strikeoutColor != null) {
            addCGColor(
                ts.strikeoutColor.handle,
                attribString,
                NSAccessibilityStrikethroughColorTextAttribute,
                attributeRange);
          }
        }
        if (ts.data != null) {
          if (ts.data instanceof URL) {
            URL dataAsURL = ((URL) (ts.data));
            NSURL linkURL = NSURL.URLWithString(NSString.stringWith(dataAsURL.toExternalForm()));
            attribString.addAttribute(NSAccessibilityLinkTextAttribute, linkURL, attributeRange);
          }
        }
      }
    }
    AccessibleAttributeEvent docAttributes = new AccessibleAttributeEvent(this);
    docAttributes.indent = Integer.MAX_VALUE;
    if (accessibleAttributeListeners.size() > 0) {
      for (int i = 0; i < accessibleAttributeListeners.size(); i++) {
        AccessibleAttributeListener listener =
            ((AccessibleAttributeListener) (accessibleAttributeListeners.elementAt(i)));
        listener.getAttributes(docAttributes);
      }
    }
    if (docAttributes.indent != Integer.MAX_VALUE) {
      NSMutableDictionary paragraphDict = NSMutableDictionary.dictionaryWithCapacity(3);
      int osAlignment = 0;
      switch (docAttributes.alignment) {
        case SWT.CENTER:
          osAlignment = OS.NSCenterTextAlignment;
          break;
        case SWT.RIGHT:
          osAlignment = OS.NSRightTextAlignment;
          break;
        case SWT.LEFT:
        default:
          osAlignment = OS.NSLeftTextAlignment;
          break;
      }
      paragraphDict.setValue(
          NSNumber.numberWithInt(osAlignment), NSString.stringWith("AXTextAlignment"));
      range.location = 0;
      attribString.addAttribute(NSString.stringWith("AXParagraphStyle"), paragraphDict, range);
    }
    return attribString;
  }
}
