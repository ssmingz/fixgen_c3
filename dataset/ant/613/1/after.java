class PlaceHold {
  public String diagnoseCreationFailure(String componentName, String type) {
    StringWriter errorText = new StringWriter();
    PrintWriter out = new PrintWriter(errorText);
    out.println((("Problem: failed to create " + type) + " ") + componentName);
    boolean lowlevel = false;
    boolean jars = false;
    boolean definitions = false;
    boolean antTask;
    String home = System.getProperty(USER_HOMEDIR);
    File libDir =
        new File(home, (Launcher.ANT_PRIVATEDIR + File.separator) + Launcher.ANT_PRIVATELIB);
    AntTypeDefinition def = getDefinition(componentName);
    if (def == null) {
      boolean isAntlib = componentName.indexOf(ANTLIB_PREFIX) == 0;
      out.println("Cause: The name is undefined.");
      out.println("Action: Check the spelling.");
      out.println("Action: Check that any custom tasks/types have been declared");
      out.println("Action: Check that any <presetdef>/<macrodefs> declarations have taken place");
      if (isAntlib) {
        out.println();
        out.println("This appears to be an antlib declaration. ");
        out.println(
            "Action: check that the implementing library exists " + "in ANT_HOME/lib or in ");
        out.println("        " + libDir);
      }
      definitions = true;
    } else {
      final String classname = def.getClassName();
      antTask = classname.startsWith("org.apache.tools.ant.");
      boolean optional = classname.startsWith("org.apache.tools.ant.taskdefs.optional");
      optional |= classname.startsWith("org.apache.tools.ant.types.optional");
      Class clazz = null;
      try {
        clazz = def.innerGetTypeClass();
      } catch (ClassNotFoundException e) {
        out.println(("Cause: the class " + classname) + " was not found.");
        jars = true;
        if (optional) {
          out.println("        This looks like one of Ant's optional components");
          out.println(
              "Action: check that the appropriate optional JAR exists " + "in ANT_HOME/lib or in ");
          out.println("        " + libDir);
        } else {
          out.println("Action: check that the component has been correctly declared");
          out.println("        And that the implementing JAR is in ANT_HOME/lib or in");
          out.println("        " + libDir);
          definitions = true;
        }
      } catch (NoClassDefFoundError ncdfe) {
        jars = true;
        out.println("Cause: Could not load a dependent class " + ncdfe.getMessage());
        if (optional) {
          out.println("       It is not enough to have Ant's optional JAR, you need the JAR");
          out.println("       files that it depends upon.");
          out.println("Ant's optional task dependencies are listed in the manual.");
        } else {
          out.println("       This class may be in a separate JAR, that is not installed.");
        }
        out.println("Action: determine what extra JAR files are needed, and place them");
        out.println("        In ANT_HOME/lib or");
        out.println("        in " + libDir);
      }
      if (clazz != null) {
        try {
          def.innerCreateAndSet(clazz, project);
          out.println("The component could be instantiated.");
        } catch (NoSuchMethodException e) {
          lowlevel = true;
          out.println(("Cause: The class " + classname) + " has no compatible constructor.");
        } catch (InstantiationException e) {
          lowlevel = true;
          out.println(
              ("Cause: The class " + classname) + " is abstract and cannot be instantiated.");
        } catch (IllegalAccessException e) {
          lowlevel = true;
          out.println(
              ("Cause: The constructor for " + classname) + " is private and cannot be invoked.");
        } catch (InvocationTargetException ex) {
          lowlevel = true;
          Throwable t = ex.getTargetException();
          out.println("Cause: The constructor threw the exception");
          out.println(t.toString());
          t.printStackTrace(out);
        } catch (NoClassDefFoundError ncdfe) {
          jars = true;
          out.println(("Cause:  A class needed by class " + classname) + " cannot be found: ");
          out.println("       " + ncdfe.getMessage());
          out.println("Action: determine what extra JAR files are needed, and place them");
          out.println("        In ANT_HOME/lib or");
          out.println("        in " + libDir);
        }
      }
      out.println();
      out.println("Do not panic, this is a common problem.");
      if (definitions) {
        out.println(
            "It may just be a typing error in the build file " + "or the task/type declaration.");
      }
      if (jars) {
        out.println("The commonest cause is a missing JAR.");
      }
      if (lowlevel) {
        out.println(
            "This is quite a low level problem, which may need "
                + "consultation with the author of the task.");
        if (antTask) {
          out.println(
              "This may be the Ant team. Please file a " + "defect or contact the developer team.");
        } else {
          out.println("This does not appear to be a task bundled with Ant.");
          out.println(("Please take it up with the supplier of the third-party " + type) + ".");
          out.println("If you have written it yourself, you probably have a bug to fix.");
        }
      } else {
        out.println();
        out.println(
            "It is not an Ant bug; there is no need to file a bug"
                + " report or contact the developers.");
      }
    }
    out.flush();
    out.close();
    return errorText.toString();
  }
}
