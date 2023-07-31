class PlaceHold {
  int kEventMenuDrawItemContent(int nextHandler, int theEvent, int userData) {
    int result = super.kEventMenuDrawItemContent(nextHandler, theEvent, userData);
    if (result == OS.noErr) {
      return result;
    }
    short[] index = new short[1];
    OS.GetEventParameter(
        theEvent, kEventParamMenuItemIndex, typeMenuItemIndex, null, 2, null, index);
    MenuItem item = items[index[0] - 1];
    if (item.accelerator == 0) {
      int accelIndex = item.text.indexOf('\t');
      if (accelIndex != (-1)) {
        String accelText = item.text.substring(accelIndex + 1);
        int length = accelText.length();
        if (length != 0) {
          result = OS.CallNextEventHandler(nextHandler, theEvent);
          Rect rect = new Rect();
          OS.GetEventParameter(
              theEvent, kEventParamMenuItemBounds, typeQDRectangle, null, sizeof, null, rect);
          int[] context = new int[1];
          OS.GetEventParameter(
              theEvent, kEventParamCGContextRef, typeCGContextRef, null, 4, null, context);
          int modifierIndex = modifierIndex(accelText);
          char[] buffer = new char[(length - modifierIndex) - 1];
          accelText.getChars(modifierIndex + 1, length, buffer, 0);
          int font = OS.kThemeMenuItemFont;
          if (buffer.length > 1) {
            font = OS.kThemeMenuItemCmdKeyFont;
          }
          byte[] family = new byte[256];
          short[] size = new short[1];
          byte[] style = new byte[1];
          OS.GetThemeFont(((short) (font)), ((short) (smSystemScript)), family, size, style);
          FontInfo info = new FontInfo();
          OS.FetchFontInfo(family[0], size[0], style[0], info);
          int[] metric = new int[1];
          OS.GetThemeMetric(kThemeMetricMenuIconTrailingEdgeMargin, metric);
          int str = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
          Point size1 = new Point();
          OS.GetThemeTextDimensions(str, ((short) (font)), 0, false, size1, null);
          rect.left = ((short) ((rect.right - Math.max(info.widMax, size1.h)) - metric[0]));
          OS.DrawThemeTextBox(
              str,
              ((short) (font)),
              kThemeStateActive,
              false,
              rect,
              ((short) (teFlushLeft)),
              context[0]);
          OS.CFRelease(str);
          if (modifierIndex != (-1)) {
            buffer = new char[modifierIndex + 1];
            accelText.getChars(0, buffer.length, buffer, 0);
            str = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
            OS.GetThemeTextDimensions(
                str, ((short) (kThemeMenuItemCmdKeyFont)), 0, false, size1, null);
            rect.right = rect.left;
            rect.left = ((short) (rect.right - size1.h));
            OS.DrawThemeTextBox(
                str,
                ((short) (kThemeMenuItemCmdKeyFont)),
                kThemeStateActive,
                false,
                rect,
                ((short) (teFlushLeft)),
                context[0]);
            OS.CFRelease(str);
          }
          return result;
        }
      }
    }
    return OS.eventNotHandledErr;
  }
}
