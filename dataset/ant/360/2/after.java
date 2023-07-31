class PlaceHold {
  private static void printUsage() {
    String lSep = System.getProperty("line.separator");
    StringBuffer msg = new StringBuffer();
    msg.append("ant [options] [target]" + lSep);
    msg.append("Options: " + lSep);
    msg.append("  -help                  print this message" + lSep);
    msg.append("  -projecthelp           print project help information" + lSep);
    msg.append("  -version               print the version information and exit" + lSep);
    msg.append("  -quiet                 be extra quiet" + lSep);
    msg.append("  -verbose               be extra verbose" + lSep);
    msg.append("  -emacs                 produce logging information without adornments" + lSep);
    msg.append("  -logfile <file>        use given file for log" + lSep);
    msg.append("  -logger <classname>    the class which is to perform logging" + lSep);
    msg.append("  -listener <classname>  add an instance of class as a project listener" + lSep);
    msg.append("  -buildfile <file>      use given buildfile" + lSep);
    msg.append("  -D<property>=<value>   use value for given property" + lSep);
    System.out.println(msg.toString());
  }
}
