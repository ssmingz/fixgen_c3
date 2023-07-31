class PlaceHold {
  int getSelectionText() {
    int[] actualSize = new int[1];
    int[] ptr = new int[1];
    if (OS.GetControlData(
            textHandle,
            ((short) (kControlEntireControl)),
            kControlEditTextCFStringTag,
            4,
            ptr,
            actualSize)
        == OS.noErr) {
      CFRange range = new CFRange();
      range.location = 0;
      range.length = OS.CFStringGetLength(ptr[0]);
      char[] buffer = new char[range.length];
      OS.CFStringGetCharacters(ptr[0], range, buffer);
      OS.CFRelease(ptr[0]);
      String string = new String(buffer);
      try {
        int value;
        if (digits > 0) {
          String decimalSeparator = getDecimalSeparator();
          int index = string.indexOf(decimalSeparator);
          if (index != (-1)) {
            String wholePart = string.substring(0, index);
            String decimalPart = string.substring(index + 1);
            if (decimalPart.length() > digits) {
              decimalPart = decimalPart.substring(0, digits);
            } else {
              int i = digits - decimalPart.length();
              for (int j = 0; j < i; j++) {
                decimalPart = decimalPart + "0";
              }
            }
            int wholeValue = Integer.parseInt(wholePart);
            int decimalValue = Integer.parseInt(decimalPart);
            for (int i = 0; i < digits; i++) {
              wholeValue *= 10;
            }
            value = wholeValue + decimalValue;
          } else {
            value = Integer.parseInt(string);
          }
        } else {
          value = Integer.parseInt(string);
        }
        int max = OS.GetControl32BitMaximum(buttonHandle);
        int min = OS.GetControl32BitMinimum(buttonHandle);
        if ((min <= value) && (value <= max)) {
          return value;
        }
      } catch (NumberFormatException e) {
      }
    }
    return -1;
  }
}
