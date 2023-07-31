class PlaceHold {
  public void execute(Project project) throws BuildException {
    PrintStream sOut = System.out;
    PrintStream sErr = System.err;
    final String classname = javaCommand.getExecutable();
    final Object[] argument = new Object[] {javaCommand.getArguments()};
    try {
      if (sysProperties != null) {
        sysProperties.setSystem();
      }
      if (out != null) {
        System.setErr(out);
        System.setOut(out);
      }
      final Class[] param = new Class[] {Class.forName("[Ljava.lang.String;")};
      Class target = null;
      if (classpath == null) {
        target = Class.forName(classname);
      } else {
        AntClassLoader loader = new AntClassLoader(project, classpath, false);
        loader.setIsolated(true);
        target = loader.forceLoadClass(classname);
      }
      final Method main = target.getMethod("main", param);
      main.invoke(null, argument);
    } catch (NullPointerException e) {
      throw new BuildException("Could not find main() method in " + classname);
    } catch (ClassNotFoundException e) {
      throw new BuildException(
          ("Could not find " + classname) + ". Make sure you have it in your classpath");
    } catch (InvocationTargetException e) {
      Throwable t = e.getTargetException();
      if (!(t instanceof SecurityException)) {
        throw new BuildException(t);
      }
    } catch (Exception e) {
      throw new BuildException(e);
    } finally {
      if (sysProperties != null) {
        sysProperties.restoreSystem();
      }
      if (out != null) {
        System.setOut(sOut);
        System.setErr(sErr);
        out.close();
      }
    }
  }
}
