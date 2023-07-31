class PlaceHold {
  int coreEventProc(int theAppleEvent, int reply, int handlerRefcon) {
    switch (handlerRefcon) {
      case OS.kAEOpenDocuments:
        AEDesc docList = new AEDesc();
        int[] count = new int[1];
        OS.AEGetParamDesc(theAppleEvent, keyDirectObject, typeAEList, docList);
        OS.AECountItems(docList, count);
        for (int index = 1; index <= count[0]; index++) {
          int[] theAEKeyword = new int[1];
          int[] typeCode = new int[1];
          int maximumSize = 80;
          int dataPtr = OS.NewPtr(maximumSize);
          int[] actualSize = new int[1];
          int status =
              OS.AEGetNthPtr(
                  docList, 1, typeFSRef, theAEKeyword, typeCode, dataPtr, maximumSize, actualSize);
          if ((status == OS.noErr) && (typeCode[0] == OS.typeFSRef)) {
            byte[] fsRef = new byte[actualSize[0]];
            OS.memmove(fsRef, dataPtr, actualSize[0]);
            int dirUrl = OS.CFURLCreateFromFSRef(kCFAllocatorDefault, fsRef);
            int dirString = OS.CFURLCopyFileSystemPath(dirUrl, kCFURLPOSIXPathStyle);
            OS.CFRelease(dirUrl);
            int length = OS.CFStringGetLength(dirString);
            char[] buffer = new char[length];
            CFRange range = new CFRange();
            range.length = length;
            OS.CFStringGetCharacters(dirString, range, buffer);
            OS.CFRelease(dirString);
            String string = new String(buffer);
            Event event = new Event();
            event.text = string;
            sendEvent(OpenDocument, event);
          }
          OS.DisposePtr(dataPtr);
        }
        OS.AEDisposeDesc(docList);
        break;
      case OS.kAEQuitApplication:
        if (!disposing) {
          Event event = new Event();
          sendEvent(Close, event);
          if (event.doit) {
            dispose();
          } else {
            return OS.userCanceledErr;
          }
        }
        break;
    }
    return OS.noErr;
  }
}
