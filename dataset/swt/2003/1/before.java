class PlaceHold {
  int kEventMenuMeasureItemWidth(int nextHandler, int theEvent, int userData) {
    short[] index = new short[1];
    OS.GetEventParameter(
        theEvent, kEventParamMenuItemIndex, typeMenuItemIndex, null, 2, null, index);
    MenuItem item = items[index[0] - 1];
    if (item.accelerator == 0) {
      int accelIndex = item.text.indexOf('\t');
      if (accelIndex != (-1)) {
        String accelText = item.text.substring(accelIndex + 1);
        if (accelText.length() != 0) {
          int result = OS.CallNextEventHandler(nextHandler, theEvent);
          char[] buffer = new char[accelText.length()];
          accelText.getChars(0, buffer.length, buffer, 0);
          int str = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, buffer.length);
          Point size = new Point();
          OS.GetThemeTextDimensions(
              str, ((short) (kThemeMenuItemCmdKeyFont)), 0, false, size, null);
          OS.CFRelease(str);
          short[] width = new short[1];
          OS.GetEventParameter(
              theEvent, kEventParamMenuItemWidth, typeSInt16, null, 2, null, width);
          int[] metric = new int[1];
          OS.GetThemeMetric(kThemeMetricMenuTextTrailingEdgeMargin, metric);
          width[0] += metric[0] + size.h;
          OS.SetEventParameter(theEvent, kEventParamMenuItemWidth, typeSInt16, 2, width);
          return result;
        }
      }
    }
    return OS.eventNotHandledErr;
  }
}
