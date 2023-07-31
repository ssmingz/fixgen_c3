class PlaceHold {
  TextStyle getStyle(NSDictionary attribs) {
    NSArray keys = attribs.allKeys();
    int count = keys.count();
    TextStyle style = new TextStyle();
    for (int j = 0; j < count; j++) {
      NSString key = new NSString(keys.objectAtIndex(j));
      if (key.isEqualTo(NSBackgroundColorAttributeName)) {
        NSColor color =
            new NSColor(attribs.objectForKey(key))
                .colorUsingColorSpaceName(NSCalibratedRGBColorSpace);
        float[] rgbColor =
            new float[] {
              color.redComponent(),
              color.greenComponent(),
              color.blueComponent(),
              color.alphaComponent()
            };
        style.background = Color.cocoa_new(display, rgbColor);
      } else if (key.isEqualTo(NSForegroundColorAttributeName)) {
        NSColor color =
            new NSColor(attribs.objectForKey(key))
                .colorUsingColorSpaceName(NSCalibratedRGBColorSpace);
        float[] rgbColor =
            new float[] {
              color.redComponent(),
              color.greenComponent(),
              color.blueComponent(),
              color.alphaComponent()
            };
        style.foreground = Color.cocoa_new(display, rgbColor);
      } else if (key.isEqualTo(NSUnderlineColorAttributeName)) {
        NSColor color =
            new NSColor(attribs.objectForKey(key))
                .colorUsingColorSpaceName(NSCalibratedRGBColorSpace);
        float[] rgbColor =
            new float[] {
              color.redComponent(),
              color.greenComponent(),
              color.blueComponent(),
              color.alphaComponent()
            };
        style.underlineColor = Color.cocoa_new(display, rgbColor);
      } else if (key.isEqualTo(NSUnderlineStyleAttributeName)) {
        NSNumber value = new NSNumber(attribs.objectForKey(key));
        switch (value.intValue()) {
          case OS.NSUnderlineStyleSingle:
            style.underlineStyle = SWT.UNDERLINE_SINGLE;
            break;
          case OS.NSUnderlineStyleDouble:
            style.underlineStyle = SWT.UNDERLINE_DOUBLE;
            break;
          case OS.NSUnderlineStyleThick:
            style.underlineStyle = UNDERLINE_THICK;
            break;
        }
        style.underline = value.intValue() != OS.NSUnderlineStyleNone;
      } else if (key.isEqualTo(NSStrikethroughColorAttributeName)) {
        NSColor color =
            new NSColor(attribs.objectForKey(key))
                .colorUsingColorSpaceName(NSCalibratedRGBColorSpace);
        float[] rgbColor =
            new float[] {
              color.redComponent(),
              color.greenComponent(),
              color.blueComponent(),
              color.alphaComponent()
            };
        style.strikeoutColor = Color.cocoa_new(display, rgbColor);
      } else if (key.isEqualTo(NSStrikethroughStyleAttributeName)) {
        NSNumber value = new NSNumber(attribs.objectForKey(key));
        style.strikeout = value.intValue() != OS.NSUnderlineStyleNone;
      } else if (key.isEqualTo(NSFontAttributeName)) {
        NSFont font = new NSFont(attribs.objectForKey(key));
        font.retain();
        style.font = Font.cocoa_new(display, font);
      }
    }
    return style;
  }
}
