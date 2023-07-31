class PlaceHold {
  boolean setKeyState(Event event, int type, NSEvent nsEvent) {
    boolean isNull = false;
    int keyCode = nsEvent.keyCode();
    event.keyCode = Display.translateKey(keyCode);
    switch (event.keyCode) {
      case SWT.LF:
        {
          event.keyCode = SWT.KEYPAD_CR;
          event.character = '\r';
          break;
        }
      case SWT.BS:
        event.character = '\b';
        break;
      case SWT.CR:
        event.character = '\r';
        break;
      case SWT.DEL:
        event.character = 0x7f;
        break;
      case SWT.ESC:
        event.character = 0x1b;
        break;
      case SWT.TAB:
        event.character = '\t';
        break;
      default:
        if ((event.keyCode == 0)
            || ((SWT.KEYPAD_MULTIPLY <= event.keyCode) && (event.keyCode <= SWT.KEYPAD_CR))) {
          NSString chars = nsEvent.characters();
          if (chars.length() > 0) {
            event.character = ((char) (chars.characterAtIndex(0)));
          }
        }
        if (event.keyCode == 0) {
          int uchrPtr = 0;
          int currentKbd = OS.TISCopyCurrentKeyboardInputSource();
          int uchrCFData =
              OS.TISGetInputSourceProperty(currentKbd, OS.kTISPropertyUnicodeKeyLayoutData());
          if (uchrCFData != 0) {
            if (uchrCFData != display.currentKeyboardUCHRdata) {
              display.deadKeyState[0] = 0;
            }
            uchrPtr = OS.CFDataGetBytePtr(uchrCFData);
            if ((uchrPtr != 0) && (OS.CFDataGetLength(uchrCFData) > 0)) {
              int cgEvent = nsEvent.CGEvent();
              long keyboardType =
                  OS.CGEventGetIntegerValueField(cgEvent, kCGKeyboardEventKeyboardType);
              int maxStringLength = 256;
              char[] output = new char[maxStringLength];
              int[] actualStringLength = new int[1];
              OS.UCKeyTranslate(
                  uchrPtr,
                  ((short) (keyCode)),
                  ((short) (kUCKeyActionDown)),
                  0,
                  ((int) (keyboardType)),
                  0,
                  display.deadKeyState,
                  maxStringLength,
                  actualStringLength,
                  output);
              if (actualStringLength[0] < 1) {
                event.keyCode = 0;
              } else {
                event.keyCode = output[0];
              }
            }
          } else {
            NSString unmodifiedChars = nsEvent.charactersIgnoringModifiers().lowercaseString();
            if (unmodifiedChars.length() > 0) {
              event.keyCode = ((char) (unmodifiedChars.characterAtIndex(0)));
            }
          }
          if (currentKbd != 0) {
            OS.CFRelease(currentKbd);
          }
        }
    }
    if ((event.keyCode == 0) && (event.character == 0)) {
      if (!isNull) {
        return false;
      }
    }
    setLocationMask(event, nsEvent);
    setInputState(event, nsEvent, type);
    return true;
  }
}
