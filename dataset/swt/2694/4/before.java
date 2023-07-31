class PlaceHold {
  int helpProc(
      int inControl, int inGlobalMouse, int inRequest, int outContentProvided, int ioHelpContent) {
    switch (inRequest) {
      case OS.kHMSupplyContent:
        {
          int[] contentProvided = new int[] {OS.kHMContentNotProvidedDontPropagate};
          if ((toolTipText != null) && (toolTipText.length() != 0)) {
            char[] buffer = new char[toolTipText.length()];
            toolTipText.getChars(0, buffer.length, buffer, 0);
            int i = 0;
            int j = 0;
            while (i < buffer.length) {
              if ((buffer[j++] = buffer[i++]) == Mnemonic) {
                if (i == buffer.length) {
                  continue;
                }
                if (buffer[i] == Mnemonic) {
                  i++;
                  continue;
                }
                j--;
              }
            }
            if (display.helpString != 0) {
              OS.CFRelease(helpString);
            }
            display.helpString = OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, j);
            HMHelpContentRec helpContent = new HMHelpContentRec();
            OS.memcpy(helpContent, ioHelpContent, sizeof);
            helpContent.version = OS.kMacHelpVersion;
            int cursorHeight = 16;
            helpContent.tagSide = ((short) (OS.kHMAbsoluteCenterAligned));
            int x = ((short) (inGlobalMouse & 0xffff));
            int y = ((short) (inGlobalMouse >> 16));
            if (display.helpControl != this) {
              display.lastHelpX = x + (cursorHeight / 2);
              display.lastHelpY = (y + cursorHeight) + (cursorHeight / 2);
            }
            int jitter = 4;
            int deltaX = Math.abs(display.lastHelpX - x) + jitter;
            int deltaY = Math.abs(display.lastHelpY - y) + jitter;
            x = display.lastHelpX - deltaX;
            y = display.lastHelpY - deltaY;
            int width = deltaX * 2;
            int height = deltaY * 2;
            display.helpControl = this;
            helpContent.absHotRect_left = ((short) (x));
            helpContent.absHotRect_top = ((short) (y));
            helpContent.absHotRect_right = ((short) (x + width));
            helpContent.absHotRect_bottom = ((short) (y + height));
            helpContent.content0_contentType = OS.kHMCFStringContent;
            helpContent.content0_tagCFString = display.helpString;
            helpContent.content1_contentType = OS.kHMCFStringContent;
            helpContent.content1_tagCFString = display.helpString;
            OS.memcpy(ioHelpContent, helpContent, sizeof);
            contentProvided[0] = OS.kHMContentProvided;
          }
          OS.memcpy(outContentProvided, contentProvided, 4);
          break;
        }
      case OS.kHMDisposeContent:
        {
          if (display.helpString != 0) {
            OS.CFRelease(helpString);
          }
          display.helpString = 0;
          break;
        }
    }
    return OS.noErr;
  }
}
