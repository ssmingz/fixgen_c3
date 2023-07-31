class PlaceHold {
  @Test
  public void testIgnoreNegativeOctalHex() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(MagicNumberCheck.class);
    checkConfig.addAttribute("ignoreNumbers", "-9223372036854775808, -2147483648, -1, 0, 1, 2");
    checkConfig.addAttribute("tokens", "NUM_INT, NUM_LONG");
    checkConfig.addAttribute("ignoreAnnotation", "true");
    final String[] expected =
        new String[] {
          "41:26: '3' is a magic number.",
          "43:27: '3' is a magic number.",
          "43:31: '4' is a magic number.",
          "45:29: '3' is a magic number.",
          "47:23: '3' is a magic number.",
          "50:22: '3' is a magic number.",
          "50:29: '5' is a magic number.",
          "50:37: '3' is a magic number.",
          "54:26: '3' is a magic number.",
          "55:39: '3' is a magic number.",
          "60:25: '010' is a magic number.",
          "61:25: '011' is a magic number.",
          "63:30: '010L' is a magic number.",
          "64:30: '011l' is a magic number.",
          "68:24: '0x10' is a magic number.",
          "69:24: '0X011' is a magic number.",
          "71:29: '0x10L' is a magic number.",
          "72:29: '0X11l' is a magic number.",
          "85:28: '3' is a magic number.",
          "92:14: '0xffffffffL' is a magic number.",
          "100:30: '+3' is a magic number.",
          "101:29: '-2' is a magic number.",
          "131:20: '378' is a magic number.",
          "160:16: '31' is a magic number.",
          "165:16: '42' is a magic number.",
          "170:16: '13' is a magic number.",
          "174:15: '21' is a magic number.",
          "178:15: '37' is a magic number.",
          "182:15: '101' is a magic number."
        };
    verify(checkConfig, getPath("InputMagicNumber.java"), expected);
  }
}
