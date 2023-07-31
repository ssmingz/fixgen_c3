class PlaceHold {
  int getSelectionText() {
    int length = OS.GetWindowTextLength(hwndText);
    TCHAR buffer = new TCHAR(getCodePage(), length + 1);
    OS.GetWindowText(hwndText, buffer, length + 1);
    String string = buffer.toString(0, length);
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
      int[] max = new int[1];
      int[] min = new int[1];
      OS.SendMessage(hwndUpDown, UDM_GETRANGE32, min, max);
      if ((min[0] <= value) && (value <= max[0])) {
        return value;
      }
    } catch (NumberFormatException e) {
    }
    return -1;
  }
}
