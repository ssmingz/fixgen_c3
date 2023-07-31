class PlaceHold {
  public boolean post(Event event) {
    synchronized (Device.class) {
      if (isDisposed()) {
        error(ERROR_DEVICE_DISPOSED);
      }
      if (event == null) {
        error(ERROR_NULL_ARGUMENT);
      }
      if (!eventSourceDelaySet) {
        OS.CGSetLocalEventsSuppressionInterval(0.0);
        OS.CGEnableEventStateCombining(1);
        OS.CGSetLocalEventsFilterDuringSuppressionState(
            (OS.kCGEventFilterMaskPermitLocalKeyboardEvents
                    | OS.kCGEventFilterMaskPermitLocalMouseEvents)
                | OS.kCGEventFilterMaskPermitSystemDefinedEvents,
            kCGEventSuppressionStateSuppressionInterval);
        OS.CGSetLocalEventsFilterDuringSuppressionState(
            (OS.kCGEventFilterMaskPermitLocalKeyboardEvents
                    | OS.kCGEventFilterMaskPermitLocalMouseEvents)
                | OS.kCGEventFilterMaskPermitSystemDefinedEvents,
            kCGEventSuppressionStateRemoteMouseDrag);
        eventSourceDelaySet = true;
      }
      int type = event.type;
      switch (type) {
        case SWT.KeyDown:
        case SWT.KeyUp:
          {
            short vKey = ((short) (Display.untranslateKey(event.keyCode)));
            if (vKey == 0) {
              int uchrPtr = 0;
              int currentKbd = OS.TISCopyCurrentKeyboardInputSource();
              int uchrCFData =
                  OS.TISGetInputSourceProperty(currentKbd, OS.kTISPropertyUnicodeKeyLayoutData());
              if (uchrCFData == 0) {
                return false;
              }
              uchrPtr = OS.CFDataGetBytePtr(uchrCFData);
              if (uchrPtr == 0) {
                return false;
              }
              if (OS.CFDataGetLength(uchrCFData) == 0) {
                return false;
              }
              int maxStringLength = 256;
              vKey = -1;
              char[] output = new char[maxStringLength];
              int[] actualStringLength = new int[1];
              for (short i = 0; i <= 0x7f; i++) {
                OS.UCKeyTranslate(
                    uchrPtr,
                    i,
                    ((short) (type == SWT.KeyDown ? OS.kUCKeyActionDown : OS.kUCKeyActionUp)),
                    0,
                    OS.LMGetKbdType(),
                    0,
                    deadKeyState,
                    maxStringLength,
                    actualStringLength,
                    output);
                if (output[0] == event.character) {
                  vKey = i;
                  break;
                }
              }
              if (vKey == (-1)) {
                for (short i = 0; i <= 0x7f; i++) {
                  OS.UCKeyTranslate(
                      uchrPtr,
                      i,
                      ((short) (type == SWT.KeyDown ? OS.kUCKeyActionDown : OS.kUCKeyActionUp)),
                      shiftKey,
                      OS.LMGetKbdType(),
                      0,
                      deadKeyState,
                      maxStringLength,
                      actualStringLength,
                      output);
                  if (output[0] == event.character) {
                    vKey = i;
                    break;
                  }
                }
              }
              if (vKey == (-1)) {
                return false;
              }
            }
            return OS.CGPostKeyboardEvent(((short) (0)), vKey, type == SWT.KeyDown) == 0;
          }
        case SWT.MouseDown:
        case SWT.MouseMove:
        case SWT.MouseUp:
          {
            CGPoint mouseCursorPosition = new CGPoint();
            int chord = OS.GetCurrentButtonState();
            mouseCursorPosition.x = event.x;
            mouseCursorPosition.y = event.y;
            if (type == SWT.MouseMove) {
              return OS.CGPostMouseEvent(
                      mouseCursorPosition,
                      true,
                      5,
                      (chord & 0x1) != 0,
                      (chord & 0x2) != 0,
                      (chord & 0x4) != 0,
                      (chord & 0x8) != 0,
                      (chord & 0x10) != 0)
                  == 0;
            } else {
              int button = event.button;
              if ((button < 1) || (button > 5)) {
                return false;
              }
              boolean button1 = false;
              boolean button2 = false;
              boolean button3 = false;
              boolean button4 = false;
              boolean button5 = false;
              switch (button) {
                case 1:
                  {
                    button1 = type == SWT.MouseDown;
                    button2 = (chord & 0x4) != 0;
                    button3 = (chord & 0x2) != 0;
                    button4 = (chord & 0x8) != 0;
                    button5 = (chord & 0x10) != 0;
                    break;
                  }
                case 2:
                  {
                    button1 = (chord & 0x1) != 0;
                    button2 = type == SWT.MouseDown;
                    button3 = (chord & 0x2) != 0;
                    button4 = (chord & 0x8) != 0;
                    button5 = (chord & 0x10) != 0;
                    break;
                  }
                case 3:
                  {
                    button1 = (chord & 0x1) != 0;
                    button2 = (chord & 0x4) != 0;
                    button3 = type == SWT.MouseDown;
                    button4 = (chord & 0x8) != 0;
                    button5 = (chord & 0x10) != 0;
                    break;
                  }
                case 4:
                  {
                    button1 = (chord & 0x1) != 0;
                    button2 = (chord & 0x4) != 0;
                    button3 = (chord & 0x2) != 0;
                    button4 = type == SWT.MouseDown;
                    button5 = (chord & 0x10) != 0;
                    break;
                  }
                case 5:
                  {
                    button1 = (chord & 0x1) != 0;
                    button2 = (chord & 0x4) != 0;
                    button3 = (chord & 0x2) != 0;
                    button4 = (chord & 0x8) != 0;
                    button5 = type == SWT.MouseDown;
                    break;
                  }
              }
              return OS.CGPostMouseEvent(
                      mouseCursorPosition, true, 5, button1, button3, button2, button4, button5)
                  == 0;
            }
          }
        case SWT.MouseWheel:
          {
            return OS.CGPostScrollWheelEvent(1, event.count) == 0;
          }
      }
      return false;
    }
  }
}
