class PlaceHold {
  private static void printUsage() {
    String lSep = System.getProperty("line.separator");
    StringBuffer msg = new StringBuffer();
    msg.append("ant [options] [target]" + lSep);
    msg.append("Options: " + lSep);
    msg.append("  -help                  print this message" + lSep);
    msg.append("  -quiet                 be extra quiet" + lSep);
    msg.append("  -verbose               be extra verbose" + lSep);
    msg.append("  -logfile <file>        use given file for log" + lSep);
    msg.append("  -buildfile <file>      use given buildfile" + lSep);
    msg.append("  -D<property>=<value>   use value for given property" + lSep);
    System.out.println(msg.toString());
  }
}
