class PlaceHold {
  public int[] getLineOffsets() {
    checkLayout();
    computeRuns();
    int lineCount = OS.pango_layout_get_line_count(layout);
    int[] offsets = new int[lineCount + 1];
    int ptr = OS.pango_layout_get_text(layout);
    PangoLayoutLine line = new PangoLayoutLine();
    for (int i = 0; i < lineCount; i++) {
      int linePtr = OS.pango_layout_get_line(layout, i);
      OS.memmove(line, linePtr, sizeof);
      int pos = ((int) (OS.g_utf8_pointer_to_offset(ptr, ptr + line.start_index)));
      offsets[i] = untranslateOffset(pos);
    }
    offsets[lineCount] = text.length();
    return offsets;
  }
}
