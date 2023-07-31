class PlaceHold {
  private String macroSubs(String s, Map macroMapping) {
    if (s == null) {
      return null;
    }
    StringBuffer ret = new StringBuffer();
    StringBuffer macroName = null;
    int state = STATE_NORMAL;
    for (int i = 0; i < s.length(); ++i) {
      char ch = s.charAt(i);
      switch (state) {
        case STATE_NORMAL:
          if (ch == '@') {
            state = STATE_EXPECT_BRACKET;
          } else {
            ret.append(ch);
          }
          break;
        case STATE_EXPECT_BRACKET:
          if (ch == '{') {
            state = STATE_EXPECT_NAME;
            macroName = new StringBuffer();
          } else if (ch == '@') {
            state = STATE_NORMAL;
            ret.append('@');
          } else {
            state = STATE_NORMAL;
            ret.append('@');
            ret.append(ch);
          }
          break;
        case STATE_EXPECT_NAME:
          if (ch == '}') {
            state = STATE_NORMAL;
            String name = macroName.toString().toLowerCase(Locale.ENGLISH);
            String value = ((String) (macroMapping.get(name)));
            if (value == null) {
              ret.append("@{");
              ret.append(name);
              ret.append("}");
            } else {
              ret.append(value);
            }
            macroName = null;
          } else {
            macroName.append(ch);
          }
          break;
        default:
          break;
      }
    }
    switch (state) {
      case STATE_NORMAL:
        break;
      case STATE_EXPECT_BRACKET:
        ret.append('@');
        break;
      case STATE_EXPECT_NAME:
        ret.append("@{");
        ret.append(macroName.toString());
        break;
      default:
        break;
    }
    return ret.toString();
  }
}
