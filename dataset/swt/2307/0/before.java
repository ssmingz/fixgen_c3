class FontData {
  public FontData(String string) {
    if (string == null) {
      SWT.error(ERROR_NULL_ARGUMENT);
    }
    int start = 0;
    int end = string.indexOf('|');
    if (end == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    String version1 = string.substring(start, end);
    try {
      if (Integer.parseInt(version1) != 1) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
    } catch (NumberFormatException e) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    start = end + 1;
    end = string.indexOf('|', start);
    if (end == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    String name = string.substring(start, end);
    start = end + 1;
    end = string.indexOf('|', start);
    if (end == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int height = 0;
    try {
      height = Integer.parseInt(string.substring(start, end));
    } catch (NumberFormatException e) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    start = end + 1;
    end = string.indexOf('|', start);
    if (end == (-1)) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    int style = 0;
    try {
      style = Integer.parseInt(string.substring(start, end));
    } catch (NumberFormatException e) {
      SWT.error(ERROR_INVALID_ARGUMENT);
    }
    start = end + 1;
    end = string.indexOf('|', start);
    data = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
    data.lfCharSet = OS.DEFAULT_CHARSET;
    setName(name);
    setHeight(height);
    setStyle(style);
    if (end == (-1)) {
      return;
    }
    String platform = string.substring(start, end);
    start = end + 1;
    end = string.indexOf('|', start);
    if (end == (-1)) {
      return;
    }
    String version2 = string.substring(start, end);
    if (platform.equals("WINDOWS") && version2.equals("1")) {
      LOGFONT newData = (OS.IsUnicode) ? ((LOGFONT) (new LOGFONTW())) : new LOGFONTA();
      try {
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfHeight = Integer.parseInt(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfWidth = Integer.parseInt(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfEscapement = Integer.parseInt(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfOrientation = Integer.parseInt(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfWeight = Integer.parseInt(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfItalic = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfUnderline = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfStrikeOut = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfCharSet = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfOutPrecision = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfClipPrecision = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfQuality = Byte.parseByte(string.substring(start, end));
        start = end + 1;
        end = string.indexOf('|', start);
        if (end == (-1)) {
          return;
        }
        newData.lfPitchAndFamily = Byte.parseByte(string.substring(start, end));
        start = end + 1;
      } catch (NumberFormatException e) {
        setName(name);
        setHeight(height);
        setStyle(style);
        return;
      }
      TCHAR buffer = new TCHAR(0, string.substring(start), false);
      int length = Math.min(OS.LF_FACESIZE - 1, buffer.length());
      if (OS.IsUnicode) {
        char[] lfFaceName = ((LOGFONTW) (newData)).lfFaceName;
        System.arraycopy(buffer.chars, 0, lfFaceName, 0, length);
      } else {
        byte[] lfFaceName = ((LOGFONTA) (newData)).lfFaceName;
        System.arraycopy(buffer.bytes, 0, lfFaceName, 0, length);
      }
      data = newData;
    }
  }
}
