class PlaceHold {
  void setSelection(int value, boolean setPos, boolean setText, boolean notify) {
    if (setPos) {
      ((NSStepper) (buttonView)).setDoubleValue(value);
    }
    if (setText) {
      String string = String.valueOf(value);
      if (digits > 0) {
        String decimalSeparator = ".";
        int index = string.length() - digits;
        StringBuffer buffer = new StringBuffer();
        if (index > 0) {
          buffer.append(string.substring(0, index));
          buffer.append(decimalSeparator);
          buffer.append(string.substring(index));
        } else {
          buffer.append("0");
          buffer.append(decimalSeparator);
          while ((index++) < 0) {
            buffer.append("0");
          }
          buffer.append(string);
        }
        string = buffer.toString();
      }
      NSCell cell = new NSCell(textView.cell());
      if (hooks(Verify) || filters(Verify)) {
        int length = cell.title().length();
        string = verifyText(string, 0, length, null);
        if (string == null) {
          return;
        }
      }
      cell.setTitle(NSString.stringWith(string));
      sendEvent(Modify);
    }
    if (notify) {
      sendEvent(Selection);
    }
  }
}
