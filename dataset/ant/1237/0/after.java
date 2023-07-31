class PlaceHold {
  public void executeNonForked(final TaskContext context) throws TaskException {
    if (m_className == null) {
      throw new TaskException("Classname must not be null.");
    }
    if (m_jar != null) {
      throw new TaskException("Cannot execute a jar in non-forked mode.");
    }
    if (m_vmArgs.size() > 0) {
      context.warn("JVM args ignored when same JVM is used.");
    }
    if (m_workingDirectory != null) {
      context.warn("Working directory ignored when same JVM is used.");
    }
    if (m_sysProperties.size() > 0) {
      context.warn("System properties ignored when same JVM is used.");
    }
    final String[] args = m_args.getArguments();
    context.debug(
        (("Running in same VM: " + m_className) + " ") + FileUtils.formatCommandLine(args));
    final Class target;
    try {
      final URL[] urls = PathUtil.toURLs(m_classPath, context);
      if (urls.length == 0) {
        target = Class.forName(m_className);
      } else {
        final URLClassLoader classLoader = new URLClassLoader(urls);
        target = classLoader.loadClass(m_className);
      }
    } catch (final Exception e) {
      throw new TaskException(("Could not find class \"" + m_className) + "\".", e);
    }
    try {
      final Class[] params = new Class[] {args.getClass()};
      final Method main = target.getMethod("main", params);
      main.invoke(null, new Object[] {args});
    } catch (final InvocationTargetException e) {
      final Throwable t = e.getTargetException();
      throw new TaskException(("Could not execute class \"" + m_className) + "\".", t);
    } catch (final Exception e) {
      throw new TaskException(("Could not execute class \"" + m_className) + "\".", e);
    }
  }
}
