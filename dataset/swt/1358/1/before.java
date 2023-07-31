class PlaceHold {
  public void test_getSegmentsChars() {
    TextLayout layout = new TextLayout(display);
    String text = "abאב.גcd";
    int textLength = text.length();
    layout.setText(text);
    String[] messages =
        new String[] {
          "no segments",
          "Embedding RTL dir test",
          "Embedding LTR dir test",
          "LRO test",
          "RLO test",
          "Traditional segments",
          "Traditional segments invalid"
        };
    int[][] segments =
        new int[][] {
          null,
          new int[] {0, 0, 4, 4, 5, 5, 8, 8},
          new int[] {0, 0, 4, 4, 5, 5, 8, 8},
          new int[] {0, textLength},
          new int[] {0, textLength},
          new int[] {0, 4, 8},
          new int[] {1}
        };
    char[][] chars =
        new char[][] {
          null,
          new char[] {'‪', '‫', '‬', '‎', '‎', '‫', '‬', '‬'},
          new char[] {'‫', '‪', '‬', '‏', '‏', '‪', '‬', '‬'},
          new char[] {0x202d, 0x202c},
          new char[] {0x202e, 0x202c},
          null,
          null
        };
    int[][] levels =
        new int[][] {
          new int[] {0, 0, 1, 1, 1, 1, 0, 0},
          new int[] {4, 4, 3, 3, 2, 3, 4, 4},
          new int[] {2, 2, 3, 3, 1, 3, 2, 2},
          new int[] {2, 2, 2, 2, 2, 2, 2, 2},
          new int[] {1, 1, 1, 1, 1, 1, 1, 1},
          new int[] {0, 0, 1, 1, 0, 1, 0, 0},
          new int[] {0, 0, 1, 1, 1, 1, 0, 0}
        };
    int[] offsets = new int[] {0, textLength};
    for (int i = 0; i < segments.length; i++) {
      layout.setSegments(segments[i]);
      layout.setSegmentsChars(chars[i]);
      assertArrayEquals(("Test line offsets" + ": group: ") + i, offsets, layout.getLineOffsets());
      for (int j = 0; j < textLength; j++) {
        assertEquals(
            (((messages[i] + ": group: ") + i) + ", index: ") + j,
            levels[i][j],
            layout.getLevel(j));
      }
    }
    layout.dispose();
  }
}
