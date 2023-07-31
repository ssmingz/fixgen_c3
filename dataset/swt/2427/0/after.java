class PlaceHold {
  static int atkText_get_text_after_offset(
      int atkObject, int offset_value, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      print("-->atkText_get_text_after_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.start = event.end = ((int) (offset_value));
        event.count = 1;
        switch (((int) (boundary_type))) {
          case ATK.ATK_TEXT_BOUNDARY_CHAR:
            event.type = ACC.TEXT_BOUNDARY_CHAR;
            break;
          case ATK.ATK_TEXT_BOUNDARY_WORD_START:
            event.type = ACC.TEXT_BOUNDARY_WORD;
            break;
          case ATK.ATK_TEXT_BOUNDARY_WORD_END:
            event.type = ACC.TEXT_BOUNDARY_WORD;
            break;
          case ATK.ATK_TEXT_BOUNDARY_SENTENCE_START:
            event.type = ACC.TEXT_BOUNDARY_SENTENCE;
            break;
          case ATK.ATK_TEXT_BOUNDARY_SENTENCE_END:
            event.type = ACC.TEXT_BOUNDARY_SENTENCE;
            break;
          case ATK.ATK_TEXT_BOUNDARY_LINE_START:
            event.type = ACC.TEXT_BOUNDARY_LINE;
            break;
          case ATK.ATK_TEXT_BOUNDARY_LINE_END:
            event.type = ACC.TEXT_BOUNDARY_LINE;
            break;
        }
        for (int i = 0; i < length; i++) {
          AccessibleTextExtendedListener listener =
              ((AccessibleTextExtendedListener) (listeners.elementAt(i)));
          listener.getText(event);
        }
        OS.memmove(start_offset, new int[] {event.start}, 4);
        OS.memmove(end_offset, new int[] {event.end}, 4);
        return getStringPtr(event.result);
      }
      int offset = ((int) (offset_value));
      String text = object.getText();
      if ((text != null) && (text.length() > 0)) {
        length = text.length();
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
        return getStringPtr(text);
      }
    }
    AtkTextIface iface = getTextIface(atkObject);
    if ((iface != null) && (iface.get_text_after_offset != 0)) {
      return ATK.call(
          iface.get_text_after_offset,
          atkObject,
          offset_value,
          boundary_type,
          start_offset,
          end_offset);
    }
    return 0;
  }
}
