package fai.services.util;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.sql.*;

import org.apache.log4j.*;

import it.swdes.database.ConnectionManager;
import it.swdes.database.DBMS;

public class Environment {

  protected static Hashtable environment;

  public static void setEnvironment(Hashtable env) {
    environment = env;
  }

  public static Hashtable getEnvironment() {
    return environment;
  }

}
