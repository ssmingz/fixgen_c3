class PlaceHold {
  int helpProc(
      int inControl, int inGlobalMouse, int inRequest, int outContentProvided, int ioHelpContent) {
    switch (inRequest) {
      case OS.kHMSupplyContent:
        {
          int[] contentProvided = new int[] {OS.kHMContentNotProvided};
          if ((toolTipText != null) && (toolTipText.length() != 0)) {
            char[] buffer = new char[toolTipText.length()];
            toolTipText.getChars(0, buffer.length, buffer, 0);
            int length = fixMnemonic(buffer);
            if (display.helpString != 0) {
              OS.CFRelease(helpString);
            }
            display.helpString =
                OS.CFStringCreateWithCharacters(kCFAllocatorDefault, buffer, length);
            HMHelpContentRec helpContent = new HMHelpContentRec();
            OS.memcpy(helpContent, ioHelpContent, sizeof);
            helpContent.version = OS.kMacHelpVersion;
            helpContent.tagSide = ((short) (OS.kHMDefaultSide));
            display.helpControl = null;
            helpContent.absHotRect_left = ((short) (0));
            helpContent.absHotRect_top = ((short) (0));
            helpContent.absHotRect_right = ((short) (0));
            helpContent.absHotRect_bottom = ((short) (0));
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
