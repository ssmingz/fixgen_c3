class PlaceHold {
  static int atkText_get_text_after_offset(
      int atkObject, int offset_value, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      System.out.println("-->atkText_get_text_after_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object == null) {
      return 0;
    }
    int offset = ((int) (offset_value));
    String text = object.getText();
    if (text != null) {
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
            int wordStart1 = nextIndexOfChar(text, " !?.\n", offset - 1);
            if (wordStart1 == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            wordStart1 = nextIndexOfNotChar(text, " !?.\n", wordStart1);
            if (wordStart1 == length) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = wordStart1;
            int wordStart2 = nextIndexOfChar(text, " !?.\n", wordStart1);
            if (wordStart2 == (-1)) {
              endBounds = length;
              break;
            }
            endBounds = nextIndexOfNotChar(text, " !?.\n", wordStart2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_WORD_END:
          {
            int previousWordEnd = previousIndexOfNotChar(text, " \n", offset);
            if ((previousWordEnd == (-1)) || (previousWordEnd != (offset - 1))) {
              offset = nextIndexOfNotChar(text, " \n", offset);
            }
            if (offset == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            int wordEnd1 = nextIndexOfChar(text, " !?.\n", ((int) (offset)));
            if (wordEnd1 == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            wordEnd1 = nextIndexOfNotChar(text, "!?.", wordEnd1);
            if (wordEnd1 == length) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = wordEnd1;
            int wordEnd2 = nextIndexOfNotChar(text, " \n", wordEnd1);
            if (wordEnd2 == length) {
              startBounds = endBounds = length;
              break;
            }
            wordEnd2 = nextIndexOfChar(text, " !?.\n", wordEnd2);
            if (wordEnd2 == (-1)) {
              endBounds = length;
              break;
            }
            endBounds = nextIndexOfNotChar(text, "!?.", wordEnd2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_SENTENCE_START:
          {
            int previousSentenceEnd = previousIndexOfChar(text, "!?.", offset);
            int previousText = previousIndexOfNotChar(text, " !?.\n", offset);
            int sentenceStart1 = 0;
            if (previousSentenceEnd >= previousText) {
              sentenceStart1 = nextIndexOfNotChar(text, " !?.\n", offset);
            } else {
              sentenceStart1 = nextIndexOfChar(text, "!?.", offset);
              if (sentenceStart1 == (-1)) {
                startBounds = endBounds = length;
                break;
              }
              sentenceStart1 = nextIndexOfNotChar(text, " !?.\n", sentenceStart1);
            }
            if (sentenceStart1 == length) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = sentenceStart1;
            int sentenceStart2 = nextIndexOfChar(text, "!?.", sentenceStart1);
            if (sentenceStart2 == (-1)) {
              endBounds = length;
              break;
            }
            endBounds = nextIndexOfNotChar(text, " !?.\n", sentenceStart2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_SENTENCE_END:
          {
            int sentenceEnd1 = nextIndexOfChar(text, "!?.", offset);
            if (sentenceEnd1 == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            sentenceEnd1 = nextIndexOfNotChar(text, "!?.", sentenceEnd1);
            if (sentenceEnd1 == length) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = sentenceEnd1;
            int sentenceEnd2 = nextIndexOfNotChar(text, " \n", sentenceEnd1);
            if (sentenceEnd2 == length) {
              startBounds = endBounds = length;
              break;
            }
            sentenceEnd2 = nextIndexOfChar(text, "!?.", sentenceEnd2);
            if (sentenceEnd2 == (-1)) {
              endBounds = length;
              break;
            }
            endBounds = nextIndexOfNotChar(text, "!?.", sentenceEnd2);
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_LINE_START:
          {
            int lineStart1 = text.indexOf('\n', offset - 1);
            if (lineStart1 == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            lineStart1 = nextIndexOfNotChar(text, "\n", lineStart1);
            if (lineStart1 == length) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = lineStart1;
            int lineStart2 = text.indexOf('\n', lineStart1);
            if (lineStart2 == (-1)) {
              endBounds = length;
              break;
            }
            lineStart2 = nextIndexOfNotChar(text, "\n", lineStart2);
            endBounds = lineStart2;
            break;
          }
        case ATK.ATK_TEXT_BOUNDARY_LINE_END:
          {
            int lineEnd1 = nextIndexOfChar(text, "\n", offset);
            if (lineEnd1 == (-1)) {
              startBounds = endBounds = length;
              break;
            }
            startBounds = lineEnd1;
            if (startBounds == length) {
              endBounds = length;
              break;
            }
            int lineEnd2 = nextIndexOfChar(text, "\n", lineEnd1 + 1);
            if (lineEnd2 == (-1)) {
              endBounds = length;
              break;
            }
            endBounds = lineEnd2;
            break;
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
    if (ATK.g_type_is_a(object.parentType, ATK_TEXT_TYPE)) {
      int superType = ATK.g_type_class_peek(object.parentType);
      AtkTextIface textIface = new AtkTextIface();
      ATK.memmove(textIface, superType);
      if (textIface.get_text_after_offset != 0) {
        return ATK.call(
            textIface.get_text_after_offset,
            object.handle,
            offset,
            boundary_type,
            start_offset,
            end_offset);
      }
    }
    return 0;
  }
}
