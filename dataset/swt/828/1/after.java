class PlaceHold {
  NSAttributedString createString() {
    NSMutableDictionary dict = NSMutableDictionary.dictionaryWithCapacity(4);
    if (foreground != null) {
      NSColor color =
          NSColor.colorWithDeviceRed(
              foreground.handle[0], foreground.handle[1], foreground.handle[2], 1);
      dict.setObject(color, NSForegroundColorAttributeName);
    }
    if (font != null) {
      dict.setObject(handle, NSFontAttributeName);
    }
    int alignment;
    if ((style & SWT.CENTER) != 0) {
      alignment = OS.NSCenterTextAlignment;
    } else if ((style & SWT.LEFT) != 0) {
      alignment = OS.NSLeftTextAlignment;
    } else {
      alignment = OS.NSRightTextAlignment;
    }
    NSMutableParagraphStyle pStyle =
        ((NSMutableParagraphStyle) (new NSMutableParagraphStyle().alloc().init()));
    pStyle.autorelease();
    pStyle.setAlignment(alignment);
    dict.setObject(pStyle, NSParagraphStyleAttributeName);
    char[] chars = new char[text.length()];
    text.getChars(0, chars.length, chars, 0);
    int length = fixMnemonic(chars);
    NSString str = NSString.stringWithCharacters(chars, length);
    NSAttributedString attribStr =
        ((NSAttributedString) (new NSAttributedString().alloc())).initWithString(str, dict);
    attribStr.autorelease();
    return attribStr;
  }
}
