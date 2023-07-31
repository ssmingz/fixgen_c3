class PlaceHold {
  static int atkText_get_text_at_offset(
      int atkObject, int offset_value, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      System.out.println(
          (((("-->atkText_get_text_at_offset: " + offset_value) + " start: ") + start_offset)
                  + " end: ")
              + end_offset);
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int offset = ((int) (offset_value));
    String text = object.getText();
    if (text.length() > 0) {
      int length = text.length();
      offset = Math.min(offset, length - 1);
      int startBounds = offset;
      int endBounds = offset;
      switch (((int) (boundary_type))) {
        case ATK.ATK_TEXT_BOUNDARY_CHAR:
          {
            if (length > offset) {
              endBounds++;
            }
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_WORD_START:
          {
            int wordStart1 = previousIndexOfNotChar(text, " !?.\n", offset);
            if (wordStart1 == (-1)) {
              startBounds = endBounds = 0;
              break;
            }
            wordStart1 = previousIndexOfChar(text, " !?.\n", wordStart1) + 1;
            if (wordStart1 == (-1)) {
              startBounds = 0;
              break;
            }
            startBounds = wordStart1;
            int wordStart2 = nextIndexOfChar(text, " !?.\n", wordStart1);
            endBounds = nextIndexOfNotChar(text, " !?.\n", wordStart2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_WORD_END:
          {
            int wordEnd1 = previousIndexOfNotChar(text, "!?.", offset + 1);
            wordEnd1 = previousIndexOfChar(text, " !?.\n", wordEnd1);
            wordEnd1 = previousIndexOfNotChar(text, " \n", wordEnd1 + 1);
            if (wordEnd1 == (-1)) {
              startBounds = endBounds = 0;
              break;
            }
            startBounds = wordEnd1 + 1;
            int wordEnd2 = nextIndexOfNotChar(text, " \n", startBounds);
            if (wordEnd2 == length) {
              endBounds = startBounds;
              break;
            }
            wordEnd2 = nextIndexOfChar(text, " !?.\n", wordEnd2);
            if (wordEnd2 == (-1)) {
              endBounds = startBounds;
              break;
            }
            endBounds = nextIndexOfNotChar(text, "!?.", wordEnd2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_SENTENCE_START:
          {
            int sentenceStart1 = previousIndexOfNotChar(text, " !?.\n", offset + 1);
            if (sentenceStart1 == (-1)) {
              startBounds = endBounds = 0;
              break;
            }
            sentenceStart1 = previousIndexOfChar(text, "!?.", sentenceStart1) + 1;
            startBounds = nextIndexOfNotChar(text, " \n", sentenceStart1);
            int sentenceStart2 = nextIndexOfChar(text, "!?.", startBounds);
            endBounds = nextIndexOfNotChar(text, " !?.\n", sentenceStart2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_SENTENCE_END:
          {
            int sentenceEnd1 = previousIndexOfNotChar(text, "!?.", offset + 1);
            sentenceEnd1 = previousIndexOfChar(text, "!?.", sentenceEnd1);
            sentenceEnd1 = previousIndexOfNotChar(text, " \n", sentenceEnd1 + 1);
            if (sentenceEnd1 == (-1)) {
              startBounds = endBounds = 0;
              break;
            }
            startBounds = sentenceEnd1 + 1;
            int sentenceEnd2 = nextIndexOfNotChar(text, " \n", startBounds);
            if (sentenceEnd2 == length) {
              endBounds = startBounds;
              break;
            }
            sentenceEnd2 = nextIndexOfChar(text, "!?.", sentenceEnd2);
            if (sentenceEnd2 == (-1)) {
              endBounds = startBounds;
              break;
            }
            endBounds = nextIndexOfNotChar(text, "!?.", sentenceEnd2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_LINE_START:
          {
            startBounds = previousIndexOfChar(text, "\n", offset) + 1;
            int lineEnd2 = nextIndexOfChar(text, "\n", startBounds);
            if (lineEnd2 < length) {
              lineEnd2++;
            }
            endBounds = lineEnd2;
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_LINE_END:
          {
            int lineEnd1 = previousIndexOfChar(text, "\n", offset);
            if (lineEnd1 == (-1)) {
              startBounds = endBounds = 0;
              break;
            }
            startBounds = lineEnd1;
            endBounds = nextIndexOfChar(text, "\n", lineEnd1 + 1);
          }
      }
      OS.memmove(start_offset, new int[] {startBounds}, 4);
      OS.memmove(end_offset, new int[] {endBounds}, 4);
      text = text.substring(startBounds, endBounds);
      byte[] bytes = Converter.wcsToMbcs(null, text, true);
      int result = OS.g_malloc(bytes.length);
      OS.memmove(result, bytes, bytes.length);
      return result;
    }
    return 0;
  }
}
