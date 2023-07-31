class PlaceHold {
  void createDisplay(DeviceData data) {
    if (OS.VERSION < 0x1030) {
      System.out.println(
          ((("***WARNING: SWT requires MacOS X version " + 10) + ".") + 3) + " or greater");
      System.out.println(
          (((("***WARNING: Detected: " + Integer.toHexString((OS.VERSION & 0xff00) >> 8)) + ".")
                      + Integer.toHexString((OS.VERSION & 0xf0) >> 4))
                  + ".")
              + Integer.toHexString(OS.VERSION & 0xf));
    }
    int identifier = OS.CFBundleGetIdentifier(OS.CFBundleGetMainBundle());
    if (identifier == 0) {
      int[] psn = new int[2];
      if (OS.GetCurrentProcess(psn) == OS.noErr) {
        int pid = OS.getpid();
        byte[] buffer = null;
        int ptr = OS.getenv(ascii("APP_NAME_" + pid));
        if (ptr != 0) {
          buffer = new byte[OS.strlen(ptr) + 1];
          OS.memmove(buffer, ptr, buffer.length);
        } else if (APP_NAME != null) {
          char[] chars = new char[APP_NAME.length()];
          APP_NAME.getChars(0, chars.length, chars, 0);
          int cfstring = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, chars, chars.length);
          if (cfstring != 0) {
            CFRange range = new CFRange();
            range.length = chars.length;
            int encoding = OS.CFStringGetSystemEncoding();
            int[] size = new int[1];
            int numChars =
                OS.CFStringGetBytes(cfstring, range, encoding, ((byte) ('?')), true, null, 0, size);
            if (numChars != 0) {
              buffer = new byte[size[0] + 1];
              numChars =
                  OS.CFStringGetBytes(
                      cfstring, range, encoding, ((byte) ('?')), true, buffer, size[0], size);
            }
            OS.CFRelease(cfstring);
          }
        }
        if (buffer != null) {
          OS.CPSSetProcessName(psn, buffer);
        }
        OS.CPSEnableForegroundOperation(psn, 0x3, 0x3c, 0x2c, 0x1103);
        OS.SetFrontProcess(psn);
        ptr = OS.getenv(ascii("APP_ICON_" + pid));
        if (ptr != 0) {
          int image = readImageRef(ptr);
          if (image != 0) {
            dockImage = image;
            OS.SetApplicationDockTileImage(dockImage);
          }
        }
      }
    }
    OS.ClearMenuBar();
    queue = OS.GetCurrentEventQueue();
    runLoop = OS.GetCFRunLoopFromEventLoop(OS.GetCurrentEventLoop());
    OS.TXNInitTextension(0, 0, 0);
    int[] bufferMode = new int[1];
    int[] bufferOptions = new int[1];
    OS.GetSystemUIMode(bufferMode, bufferOptions);
    systemUIMode = bufferMode[0];
    systemUIOptions = bufferOptions[0];
    OS.RegisterAppearanceClient();
    highlightColor = new RGBColor();
    OS.GetThemeBrushAsColor(
        ((short) (kThemeBrushPrimaryHighlightColor)), ((short) (getDepth())), true, highlightColor);
  }
}
