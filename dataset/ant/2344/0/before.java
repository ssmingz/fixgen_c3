class PlaceHold {
  public final Reader getAssembledReader() throws BuildException {
    if (primaryReader == null) {
      throw new BuildException("primaryReader must not be null.");
    }
    Reader instream = primaryReader;
    final int filterReadersCount = filterChains.size();
    final Vector finalFilters = new Vector();
    for (int i = 0; i < filterReadersCount; i++) {
      final FilterChain filterchain = ((FilterChain) (filterChains.elementAt(i)));
      final Vector filterReaders = filterchain.getFilterReaders();
      final int readerCount = filterReaders.size();
      for (int j = 0; j < readerCount; j++) {
        finalFilters.addElement(filterReaders.elementAt(j));
      }
    }
    final int filtersCount = finalFilters.size();
    if (filtersCount > 0) {
      for (int i = 0; i < filtersCount; i++) {
        Object o = finalFilters.elementAt(i);
        if (o instanceof AntFilterReader) {
          final AntFilterReader filter = ((AntFilterReader) (finalFilters.elementAt(i)));
          final String className = filter.getClassName();
          final Path classpath = filter.getClasspath();
          final Project project = filter.getProject();
          if (className != null) {
            try {
              Class clazz = null;
              if (classpath == null) {
                clazz = Class.forName(className);
              } else {
                AntClassLoader al = project.createClassLoader(classpath);
                clazz = al.loadClass(className);
                AntClassLoader.initializeClass(clazz);
              }
              if (clazz != null) {
                if (!FilterReader.class.isAssignableFrom(clazz)) {
                  throw new BuildException(className + " does not extend java.io.FilterReader");
                }
                final Constructor[] constructors = clazz.getConstructors();
                int j = 0;
                boolean consPresent = false;
                for (; j < constructors.length; j++) {
                  Class[] types = constructors[j].getParameterTypes();
                  if ((types.length == 1) && types[0].isAssignableFrom(Reader.class)) {
                    consPresent = true;
                    break;
                  }
                }
                if (!consPresent) {
                  throw new BuildException(
                      (className + " does not define a public constructor")
                          + " that takes in a Reader as its single argument.");
                }
                final Reader[] rdr = new Reader[] {instream};
                instream = ((Reader) (constructors[j].newInstance(rdr)));
                setProjectOnObject(instream);
                if (Parameterizable.class.isAssignableFrom(clazz)) {
                  final Parameter[] params = filter.getParams();
                  ((Parameterizable) (instream)).setParameters(params);
                }
              }
            } catch (final ClassNotFoundException cnfe) {
              throw new BuildException(cnfe);
            } catch (final InstantiationException ie) {
              throw new BuildException(ie);
            } catch (final IllegalAccessException iae) {
              throw new BuildException(iae);
            } catch (final InvocationTargetException ite) {
              throw new BuildException(ite);
            }
          }
        } else if (o instanceof ChainableReader) {
          setProjectOnObject(o);
          instream = ((ChainableReader) (o)).chain(instream);
          setProjectOnObject(instream);
        }
      }
    }
    return instream;
  }
}
