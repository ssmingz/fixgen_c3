class PlaceHold {
  static int atkText_get_text_before_offset(
      int atkObject, int offset_value, int boundary_type, int start_offset, int end_offset) {
    if (DEBUG) {
      print("-->atkText_get_text_before_offset");
    }
    AccessibleObject object = getAccessibleObject(atkObject);
    if (object != null) {
      Accessible accessible = object.accessible;
      Vector listeners = accessible.accessibleTextExtendedListeners;
      int length = listeners.size();
      if (length > 0) {
        AccessibleTextEvent event = new AccessibleTextEvent(accessible);
        event.start = event.end = ((int) (offset_value));
        event.count = -1;
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
              if ((length >= offset) && (offset > 0)) {
                startBounds--;
              }
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_WORD_START:
            {
              int wordStart1 = previousIndexOfChar(text, " !?.\n", offset - 1);
              if (wordStart1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              int wordStart2 = previousIndexOfNotChar(text, " !?.\n", wordStart1);
              if (wordStart2 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = wordStart1 + 1;
              startBounds = previousIndexOfChar(text, " !?.\n", wordStart2) + 1;
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_WORD_END:
            {
              int wordEnd1 = previousIndexOfChar(text, " !?.\n", offset);
              if (wordEnd1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              wordEnd1 = previousIndexOfNotChar(text, " \n", wordEnd1 + 1);
              if (wordEnd1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = wordEnd1 + 1;
              int wordEnd2 = previousIndexOfNotChar(text, " !?.\n", endBounds);
              wordEnd2 = previousIndexOfChar(text, " !?.\n", wordEnd2);
              if (wordEnd2 == (-1)) {
                startBounds = 0;
                break;
              }
              startBounds = previousIndexOfNotChar(text, " \n", wordEnd2 + 1) + 1;
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_SENTENCE_START:
            {
              int sentenceStart1 = previousIndexOfChar(text, "!?.", offset);
              if (sentenceStart1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              int sentenceStart2 = previousIndexOfNotChar(text, "!?.", sentenceStart1);
              if (sentenceStart2 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = sentenceStart1 + 1;
              startBounds = previousIndexOfChar(text, "!?.", sentenceStart2) + 1;
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_SENTENCE_END:
            {
              int sentenceEnd1 = previousIndexOfChar(text, "!?.", offset);
              if (sentenceEnd1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              sentenceEnd1 = previousIndexOfNotChar(text, " \n", sentenceEnd1 + 1);
              if (sentenceEnd1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = sentenceEnd1 + 1;
              int sentenceEnd2 = previousIndexOfNotChar(text, "!?.", endBounds);
              sentenceEnd2 = previousIndexOfChar(text, "!?.", sentenceEnd2);
              if (sentenceEnd2 == (-1)) {
                startBounds = 0;
                break;
              }
              startBounds = previousIndexOfNotChar(text, " \n", sentenceEnd2 + 1) + 1;
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_LINE_START:
            {
              int lineStart1 = previousIndexOfChar(text, "\n", offset);
              if (lineStart1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = lineStart1 + 1;
              startBounds = previousIndexOfChar(text, "\n", lineStart1) + 1;
              break;
            }
          case ATK.ATK_TEXT_BOUNDARY_LINE_END:
            {
              int lineEnd1 = previousIndexOfChar(text, "\n", offset);
              if (lineEnd1 == (-1)) {
                startBounds = endBounds = 0;
                break;
              }
              endBounds = lineEnd1;
              startBounds = previousIndexOfChar(text, "\n", lineEnd1);
              if (startBounds == (-1)) {
                startBounds = 0;
              }
              break;
            }
        }
        OS.memmove(start_offset, new int[] {startBounds}, 4);
        OS.memmove(end_offset, new int[] {endBounds}, 4);
        text = text.substring(startBounds, endBounds);
        return getStringPtr(text);
      }
    }
    return 0;
  }
}
