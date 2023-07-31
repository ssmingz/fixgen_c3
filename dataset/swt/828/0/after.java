class PlaceHold {
  NSAttributedString createString() {
    NSMutableDictionary dict = NSMutableDictionary.dictionaryWithCapacity(4);
    Color foreground = parent.foreground;
    if (foreground != null) {
      NSColor color =
          NSColor.colorWithDeviceRed(
              foreground.handle[0], foreground.handle[1], foreground.handle[2], 1);
      dict.setObject(color, NSForegroundColorAttributeName);
    }
    Font font = parent.font;
    if (font != null) {
      dict.setObject(font.handle, NSFontAttributeName);
    }
    char[] chars = new char[text.length()];
    text.getChars(0, chars.length, chars, 0);
    int length = fixMnemonic(chars);
    NSMutableParagraphStyle pStyle =
        ((NSMutableParagraphStyle) (new NSMutableParagraphStyle().alloc().init()));
    pStyle.autorelease();
    pStyle.setAlignment(NSCenterTextAlignment);
    dict.setObject(pStyle, NSParagraphStyleAttributeName);
    NSString str = NSString.stringWithCharacters(chars, length);
    NSAttributedString attribStr =
        ((NSAttributedString) (new NSAttributedString().alloc())).initWithString(str, dict);
    attribStr.autorelease();
    return attribStr;
  }
}
