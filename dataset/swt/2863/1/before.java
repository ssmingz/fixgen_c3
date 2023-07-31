class PlaceHold {
  String parse(String string) {
    int length = string.length();
    offsets = new Point[length / 4];
    ids = new String[length / 4];
    mnemonics = new int[length / 4];
    StringBuffer result = new StringBuffer();
    char[] buffer = new char[length];
    string.getChars(0, string.length(), buffer, 0);
    int index = 0;
    int state = 0;
    int linkIndex = 0;
    int start = 0;
    int tagStart = 0;
    int linkStart = 0;
    int endtagStart = 0;
    int refStart = 0;
    while (index < length) {
      char c = Character.toLowerCase(buffer[index]);
      switch (state) {
        case 0:
          if (c == '<') {
            tagStart = index;
            state++;
          }
          break;
        case 1:
          if (c == 'a') {
            state++;
          }
          break;
        case 2:
          switch (c) {
            case 'h':
              state = 7;
              break;
            case '>':
              linkStart = index + 1;
              state++;
              break;
            default:
              if (Character.isWhitespace(c)) {
                break;
              } else {
                state = 13;
              }
          }
          break;
        case 3:
          if (c == '<') {
            endtagStart = index;
            state++;
          }
          break;
        case 4:
          state = (c == '/') ? state + 1 : 3;
          break;
        case 5:
          state = (c == 'a') ? state + 1 : 3;
          break;
        case 6:
          if (c == '>') {
            mnemonics[linkIndex] = parseMnemonics(buffer, start, tagStart, result);
            int offset = result.length();
            parseMnemonics(buffer, linkStart, endtagStart, result);
            offsets[linkIndex] = new Point(offset, result.length() - 1);
            if (ids[linkIndex] == null) {
              ids[linkIndex] = new String(buffer, linkStart, endtagStart - linkStart);
            }
            linkIndex++;
            start = tagStart = linkStart = endtagStart = refStart = index + 1;
            state = 0;
          } else {
            state = 3;
          }
          break;
        case 7:
          state = (c == 'r') ? state + 1 : 0;
          break;
        case 8:
          state = (c == 'e') ? state + 1 : 0;
          break;
        case 9:
          state = (c == 'f') ? state + 1 : 0;
          break;
        case 10:
          state = (c == '=') ? state + 1 : 0;
          break;
        case 11:
          if (c == '"') {
            state++;
            refStart = index + 1;
          } else {
            state = 0;
          }
          break;
        case 12:
          if (c == '"') {
            ids[linkIndex] = new String(buffer, refStart, index - refStart);
            state = 2;
          }
          break;
        case 13:
          if (Character.isWhitespace(c)) {
            state = 0;
          } else if (c == '=') {
            state++;
          }
          break;
        case 14:
          state = (c == '"') ? state + 1 : 0;
          break;
        case 15:
          if (c == '"') {
            state = 2;
          }
          break;
        default:
          state = 0;
          break;
      }
      index++;
    }
    if (start < length) {
      int tmp = parseMnemonics(buffer, start, tagStart, result);
      int mnemonic = parseMnemonics(buffer, linkStart, index, result);
      if (mnemonic == (-1)) {
        mnemonic = tmp;
      }
      mnemonics[linkIndex] = mnemonic;
    } else {
      mnemonics[linkIndex] = -1;
    }
    if (offsets.length != linkIndex) {
      Point[] newOffsets = new Point[linkIndex];
      System.arraycopy(offsets, 0, newOffsets, 0, linkIndex);
      offsets = newOffsets;
      String[] newIDs = new String[linkIndex];
      System.arraycopy(ids, 0, newIDs, 0, linkIndex);
      ids = newIDs;
      int[] newMnemonics = new int[linkIndex + 1];
      System.arraycopy(mnemonics, 0, newMnemonics, 0, linkIndex + 1);
      mnemonics = newMnemonics;
    }
    return result.toString();
  }
}
