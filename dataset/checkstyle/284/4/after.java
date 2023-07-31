class PlaceHold {
  @Test
  public void testIgnoreSome() throws Exception {
    final DefaultConfiguration checkConfig = createCheckConfig(MagicNumberCheck.class);
    checkConfig.addAttribute("ignoreNumbers", "0, 1, 3.0, 8, 16");
    checkConfig.addAttribute("ignoreAnnotation", "true");
    final String[] expected =
        new String[] {
          "22:25: '2' is a magic number.",
          "28:35: '2' is a magic number.",
          "30:24: '2' is a magic number.",
          "32:29: '2.0' is a magic number.",
          "34:29: '2' is a magic number.",
          "36:17: '2' is a magic number.",
          "38:19: '2.0' is a magic number.",
          "42:32: '1.5' is a magic number.",
          "43:31: '4' is a magic number.",
          "48:26: '1.5' is a magic number.",
          "50:29: '5' is a magic number.",
          "61:25: '011' is a magic number.",
          "64:30: '011l' is a magic number.",
          "69:24: '0X011' is a magic number.",
          "72:29: '0X11l' is a magic number.",
          "92:14: '0xffffffffL' is a magic number.",
          "101:29: '-2' is a magic number.",
          "102:35: '+3.5' is a magic number.",
          "103:36: '-2.5' is a magic number.",
          "109:34: '0xffffffff' is a magic number.",
          "110:36: '0xffffffffffffffffL' is a magic number.",
          "111:35: '0x80000000' is a magic number.",
          "112:36: '0x8000000000000000L' is a magic number.",
          "113:36: '037777777777' is a magic number.",
          "114:38: '01777777777777777777777L' is a magic number.",
          "115:37: '020000000000' is a magic number.",
          "116:38: '01000000000000000000000L' is a magic number.",
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
